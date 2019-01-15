-- create name basics table
CREATE TABLE IF NOT EXISTS name_basics
(
	nconst varchar not null PRIMARY KEY,
	primaryName varchar not null,
	birthYear varchar not null,
	deathYear varchar default null,
	primaryProfession varchar default null,
	knownForTitles varchar not null
);

-- create title basics table
CREATE TABLE IF NOT EXISTS title_basics
(
	tconst varchar not null PRIMARY KEY,
	titleType varchar not null,
	primaryTitle varchar not null,
	originalTitle varchar not null,
	isAdult varchar not null,
	startYear varchar not null,
	endYear varchar default null,
	runtimeMinutes varchar default null,
	genres varchar not null
);

-- create title akas table
CREATE TABLE IF NOT EXISTS title_akas
(
	titleId varchar not null,
	ordering bigint not null,
	title varchar not null,
	region varchar default null,
	language varchar default null,
	types varchar default null,
	attributes varchar default null,
	isOriginalTitle varchar default null
);

-- create title crews table
CREATE TABLE IF NOT EXISTS title_crews
(
	tconst varchar not null,
	directors varchar default null,
	writers varchar default null
);


-- create title episodes table
CREATE TABLE IF NOT EXISTS title_episodes
(
	tconst varchar not null,
	parentTconst varchar not null,
	seasonNumber varchar default null,
	episodeNumber varchar default null
);


-- create title principals table
CREATE TABLE IF NOT EXISTS title_principals
(
	tconst varchar not null,
	ordering bigint not null,
	category varchar not null,
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