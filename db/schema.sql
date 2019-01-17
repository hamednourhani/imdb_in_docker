-- create name basics table
CREATE TABLE IF NOT EXISTS name_basics
(
	nconst varchar not null PRIMARY KEY,
	primaryName varchar not null,
	birthYear varchar not null,
	deathYear varchar default null,
	primaryProfession text[] default '{}'::text[],
	knownForTitles text[] default '{}'::text[]
);

-- create title basics table
CREATE TABLE IF NOT EXISTS title_basics
(
	tconst varchar not null PRIMARY KEY,
	titleType varchar not null,
	primaryTitle varchar not null,
	originalTitle varchar not null,
	isAdult boolean not null,
	startYear varchar not null,
	endYear varchar default null,
	runtimeMinutes varchar default null,
	genres text[] default '{}'::text[]
);

-- create title akas table
CREATE TABLE IF NOT EXISTS title_akas
(
	titleId varchar not null,
	ordering bigint not null,
	title varchar not null,
	region varchar default null,
	language varchar default null,
	types text[] default '{}'::text[],
	attributes text[] default '{}'::text[],
	isOriginalTitle boolean default false
);

-- create title crews table
CREATE TABLE IF NOT EXISTS title_crews
(
	tconst varchar not null,
	directors text[] default '{}'::text[],
	writers text[] default '{}'::text[]
);


-- create title episodes table
CREATE TABLE IF NOT EXISTS title_episodes
(
	tconst varchar not null,
	parentTconst varchar not null,
	seasonNumber bigint default null,
	episodeNumber bigint default null
);


-- create title principals table
CREATE TABLE IF NOT EXISTS title_principals
(
	tconst varchar not null,
	ordering bigint not null,
	nconst varchar default null,
	category varchar default null,
	job varchar default null,
	characters varchar default null
);

-- create title ratings table
CREATE TABLE IF NOT EXISTS title_ratings
(
	tconst varchar not null,
	averageRating double precision not null,
	numVotes bigint not null
);