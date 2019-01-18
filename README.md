IMDB Dummy Rest
=========================

#### Run database and populate tables

run database (`imdb`) and create sample tables :

```./run.sh```

##### note :
copy tsv files to files in `dummy/src/main/resources` according to dummy subProject application.conf.

then run `sbt dummy/run` to populate tables with data


##### test db :

`sudo -u postgres psql -d imdb -h 127.0.0.1 -p 45432`

`password : postgres`

`select * from title_akas limit 10;`

#### Run rest server

`cd` root of project `sbt rest/run`

