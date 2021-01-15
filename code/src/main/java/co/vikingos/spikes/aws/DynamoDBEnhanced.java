package co.vikingos.spikes.aws;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;

import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import org.slf4j.*;

import javax.inject.Singleton;


@Singleton
public class DynamoDBEnhanced {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBEnhanced.class);


    private final ProvisionedThroughput DEFAULT_PROVISIONED_THROUGHPUT =
            ProvisionedThroughput.builder()
                    .readCapacityUnits(50L)
                    .writeCapacityUnits(50L)
                    .build();

    private final TableSchema<BookSaved> TABLE_SCHEMA =
            StaticTableSchema.builder(BookSaved.class)
                    .newItemSupplier(BookSaved::new)
                    .addAttribute(String.class, a -> a.name("isbn")
                            .getter(BookSaved::getIsbn)
                            .setter(BookSaved::setIsbn)
                            .tags(primaryPartitionKey()))
                    .addAttribute(String.class, a -> a.name("name")
                            .getter(BookSaved::getName)
                            .setter(BookSaved::setName))
                    .build();

    // Uses the Enhanced Client to inject a new post into a DynamoDB table
    public void injectDynamoItem(BookSaved item){

        LOG.info("Se invokó la injectDynamoItem");


        try {

            Region region = Region.US_EAST_2;
            DynamoDbClient ddb = DynamoDbClient.builder()
                    .region(region)
                    .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                    .build();



            DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(ddb)
                    .build();

            LOG.info("se crea el cliente dynamodbEnhanced");

            //Create a DynamoDbTable object
            DynamoDbTable<BookSaved> mappedTable = enhancedClient.table("vikingosSpikeTerraformDynamo", TABLE_SCHEMA);

            PutItemEnhancedRequest enReq = PutItemEnhancedRequest.builder(BookSaved.class)
                    .item(item)
                    .build();

            mappedTable.putItem(enReq);

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }
}
