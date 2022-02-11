// aws dynamodb create-table \
//     --table-name Music \
//     --attribute-definitions \
//         AttributeName=Artist,AttributeType=S \
//         AttributeName=SongTitle,AttributeType=S \
//     --key-schema AttributeName=Artist,KeyType=HASH AttributeName=SongTitle,KeyType=RANGE \
//     --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
//     --table-class STANDARD

 var params = {
     TableName: 'movie',
     KeySchema: [
         {
             AttributeName: 'actor',
             KeyType: 'HASH',
         },
         {
             AttributeName: 'movie_name',
             KeyType: 'RANGE',
         }
     ],
     AttributeDefinitions: [
         {
             AttributeName: 'actor',
             AttributeType: 'S',
         },
         {
             AttributeName: 'movie_name',
             AttributeType: 'S',
         },
         {
             AttributeName: 'actor_role',
             AttributeType: 'S',
         },
         {
             AttributeName: 'year',
             AttributeType: 'N',
         },
         {
             AttributeName: 'gender',
             AttributeType: 'S',
         },

     ],
     ProvisionedThroughput: {
         ReadCapacityUnits: 1,
         WriteCapacityUnits: 1,
     },
     LocalSecondaryIndexes: [ 
         {
             IndexName: 'index_actorId_roleId',
             KeySchema: [
                 { // Required HASH type attribute - must match the table's HASH key attribute name
                     AttributeName: 'actor',
                     KeyType: 'HASH',
                 },
                 { // alternate RANGE key attribute for the secondary index
                     AttributeName: 'actor_role',
                     KeyType: 'RANGE',
                 }
             ],
             Projection: { // required
                 ProjectionType: 'ALL', // (ALL | KEYS_ONLY | INCLUDE)
             },
         },
         {
             IndexName: 'index_actorId_yearId',
             KeySchema: [
                 { // Required HASH type attribute - must match the table's HASH key attribute name
                     AttributeName: 'actor',
                     KeyType: 'HASH',
                 },
                 { // alternate RANGE key attribute for the secondary index
                     AttributeName: 'year',
                     KeyType: 'RANGE',
                 }
             ],
             Projection: { // required
                 ProjectionType: 'ALL', // (ALL | KEYS_ONLY | INCLUDE)
             },
         },
         {
             IndexName: 'index_actorId_genderId',
             KeySchema: [
                 { // Required HASH type attribute - must match the table's HASH key attribute name
                     AttributeName: 'actor',
                     KeyType: 'HASH',
                 },
                 { // alternate RANGE key attribute for the secondary index
                     AttributeName: 'gender',
                     KeyType: 'RANGE',
                 }
             ],
             Projection: { // required
                 ProjectionType: 'ALL', // (ALL | KEYS_ONLY | INCLUDE)
             },
         },
         
     ],
 };
 dynamodb.createTable(params, function(err, data) {
     if (err) ppJson(err); // an error occurred
     else ppJson(data); // successful response

 });