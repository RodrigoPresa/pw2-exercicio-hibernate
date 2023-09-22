package dev.pw2.ws;

import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import dev.pw2.model.Channel;
import dev.pw2.model.User;

@Path("/channel")
@Transactional
public class ChannelWS {

   @GET
   @Path("/save/{hash}")
   @Produces(MediaType.APPLICATION_JSON)
   public Channel save(@PathParam("hash") String hash) {
      Channel channel = new Channel(hash);
      channel.persist();
      return channel;
   }

   @GET
   @Path("/add/{idChannel}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public User add(@PathParam("idChannel") Long idChannel, @PathParam("idUser") Long idUser) {

      Channel channel = Channel.findById(idChannel);
      if (channel == null){
        throw new BadRequestException("Channel not found");
      }

      User user = User.findById(idUser);
      if (user == null){
        throw new BadRequestException("User not found");
      }

      channel.addUser(user);
      user.addChannel(channel);

      user.persist();

      return user;
   }

   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Channel> list() {
      return Channel.listAll();
   }
}