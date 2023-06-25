package com.example.vantazii.chat.socket;


import com.example.vantazii.chat.Chat;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PubnubHelper {


    private PubNub pubNub;


    public PNPublishResult sendMessage(Chat chat, String channelName) throws PubNubException {


        final JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("id", chat.getId().toString());
        messageJsonObject.addProperty("message", chat.getMessage());
        messageJsonObject.addProperty("sender", chat.getSender().getPhoneNumber());
        messageJsonObject.addProperty("reciver", chat.getReciver().getPhoneNumber());
        messageJsonObject.addProperty("createdAt", chat.getCreatedAt().toString());


        return pubNub.publish()
                .channel(channelName)
                .message(messageJsonObject)
                .sync();


    }


}
