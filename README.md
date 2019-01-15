IMDB Dummy Rest
=========================

#### Run database and populate tables

run database (`imdb`) and populate sample tables by dummy data from `dummy` folder :

```./db.sh```

##### note :
before start `db.sh` replace `tsv` files in `db/dummy` with the real one.



##### test db :

`sudo -u postgres psql -d imdb -h 127.0.0.1 -p 45432`

`password : postgres`

`select * from title_akas limit 10;`

#### Run rest server

`cd` root of project `sbt run`

