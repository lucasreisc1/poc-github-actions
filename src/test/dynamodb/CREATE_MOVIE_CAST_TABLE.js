 var params = {
     TableName: 'movie',
     KeySchema: [
         {
             AttributeName: 'actor',
             KeyType: 'HASH',
         },
         {
             AttributeName: 'name',
             KeyType: 'RANGE',
         }
     ],
     AttributeDefinitions: [
         {
             AttributeName: 'actor',
             AttributeType: 'S',
         },
         {
             AttributeName: 'name',
             AttributeType: 'S',
         },
         {
             AttributeName: 'actorRole',
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
                     AttributeName: 'actorRole',
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