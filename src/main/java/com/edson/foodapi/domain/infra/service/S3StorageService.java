package com.edson.foodapi.domain.infra.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.edson.foodapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

@Service
public class S3StorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${foodapi.storage.s3.bucket}")
    private String bucket;

    @Value("${foodapi.storage.s3.diretorio}")

    private String diretorio;

    @Override
    public RecuperarArquivo recuperar(String nomeArquivo) {
        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

        URL url = amazonS3.getUrl(this.bucket, caminhoArquivo);

        return RecuperarArquivo.builder()
                .url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {

            String path = getCaminhoArquivo(novaFoto.getNomeArquivo());

            var obejectMetaData = new ObjectMetadata();
            obejectMetaData.setContentType(novaFoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    bucket,
                    path,
                    novaFoto.getInputStream(),obejectMetaData)
                    .withCannedAcl(CannedAccessControlList.PublicRead);//Permite ler a imagem/acessar na url.

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Falha ao enviar para a amazons3 o arquivo");
        }
    }


    @Override
    public void remover(String nomeArquivo) {
        try {

            String path = getCaminhoArquivo(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(bucket, path);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Falha ao remover arquivo na amazons3",e);
        }
    }

    private String getCaminhoArquivo(String nomeArquio) {
        return String.format("%s/%s", diretorio, nomeArquio);
    }
}
