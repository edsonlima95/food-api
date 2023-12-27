package com.edson.foodapi.domain.listener;

import com.edson.foodapi.domain.event.PedidoConfirmadoEvent;
import com.edson.foodapi.domain.infra.service.SmtpEmailServiceImpl;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PedidoConfirmadoListener {

    @Autowired
    private SmtpEmailServiceImpl smtpEmailService;

    /**
     * @EventListener é o padrão, seja o evento é disparado antes de atualizar as informações
     * no banco.
     *
     * @TransactionalEventListener é enviado depois dos dados serem atualizados no banco, porem se der
     * uma exception o email nao será enviado.
     */
    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){

        Pedido pedido = event.getPedido();

        var mensagem = SendEmailService.Mensagem.builder()
                .destinatario(pedido.getCliente().getEmail())
                .assunto("Confirmação de pedido")
                .variavel("pedido",pedido)
                .corpo("enviar-email.html")
                .build();

        smtpEmailService.enviar(mensagem);

    }

}
