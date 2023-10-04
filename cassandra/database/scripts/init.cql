CREATE KEYSPACE defaultspace WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};


CREATE type defaultspace.test (
    id text,
);
