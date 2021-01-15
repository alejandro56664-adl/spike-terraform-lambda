package co.vikingos.spikes.aws;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.UUID;

@Introspected
public class BookRequestHandler extends MicronautRequestHandler<Book, BookSaved> {

    private static final Logger LOG = LoggerFactory.getLogger(BookRequestHandler.class);


    @Inject
    private DynamoDBEnhanced dde;

    @Override
    public BookSaved execute(Book input) {

        LOG.info("Se invokó la función");
        BookSaved bookSaved = new BookSaved();
        bookSaved.setName(input.getName());
        bookSaved.setIsbn(UUID.randomUUID().toString());

        //Persist submitted data into a DynamoDB table using the Enhanced Client
        dde.injectDynamoItem(bookSaved);

        return bookSaved;
    }
}
