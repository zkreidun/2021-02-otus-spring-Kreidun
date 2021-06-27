package ru.otus.spring.kreidun;


import org.junit.jupiter.api.Test;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Date;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.integration.test.matcher.MockitoMessageMatchers.messageWithHeaderEntry;
import static org.springframework.integration.test.matcher.MockitoMessageMatchers.messageWithPayload;


public class PotteryApplicationTest {

    static final Date PAYLOAD = new Date();

    static final String HEADER_VALUE = "cup";

    static final String HEADER_KEY = "test";

    @Test
    public void testMockMessage() {

        Message<?> message = MessageBuilder.withPayload(PAYLOAD)
                .setHeader(HEADER_KEY, HEADER_VALUE)
                .build();
        MessageHandler handler = mock(MessageHandler.class);
        handler.handleMessage(message);
        verify(handler).handleMessage(messageWithPayload(PAYLOAD));

        MessageChannel channel = mock(MessageChannel.class);
        when(channel.send(messageWithHeaderEntry(HEADER_KEY, is(instanceOf(Short.class)))))
                .thenReturn(true);
    }
}