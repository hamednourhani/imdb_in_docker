#!/bin/bash

docker-compose down && docker-compose up -d

sleep 5;

export PGPASSWORD='postgres';

echo "import name.basics.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy name_basics FROM './dummy/name.basics.tsv' DELIMITER E'\t' CSV HEADER;";

echo "import title.akas.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy title_akas FROM './dummy/title.akas.tsv' DELIMITER E'\t' CSV HEADER;";

echo "import title.basics.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy title_basics FROM './dummy/title.basics.tsv' DELIMITER E'\t' CSV HEADER;";

echo "import title.crews.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy title_crews FROM './dummy/title.crews.tsv' DELIMITER E'\t' CSV HEADER;";

echo "import title.episodes.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy title_episodes FROM './dummy/title.episodes.tsv' DELIMITER E'\t' CSV HEADER;";

#echo "import title.principals.tsv";
#psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
#	-c "\copy title_principals FROM './dummy/title.principals.tsv' DELIMITER E'\t' CSV HEADER;";

echo "import title.ratings.tsv";
psql -U postgres -d imdb -h 127.0.0.1 -p 45432 \
	-c "\copy title_ratings FROM './dummy/title.ratings.tsv' DELIMITER E'\t' CSV HEADER;";