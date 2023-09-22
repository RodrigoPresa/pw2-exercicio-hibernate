package dev.pw2.ws;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import dev.pw2.model.Message;
import dev.pw2.model.User;

@Path("/message")
@Transactional
public class MessageWS {

   @GET
   @Path("/save/{text}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public Message save(@PathParam("text") String text, @PathParam("idUser") Long idUser) {

      Message message = new Message();
      message.setText(text);
      message.persistAndFlush();

      User user = User.findById(idUser);
      if (user == null){
        throw new BadRequestException("User not found");
      }

      user.addMessage(message);
      user.persistAndFlush();

      return message;
   }
}