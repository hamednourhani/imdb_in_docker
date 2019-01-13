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
	isOriginalTitle boolean default false
);

-- create name basics table
CREATE TABLE IF NOT EXISTS name_basics
(
	nconst varchar not null,
	primaryName varchar not null,
	birthYear varchar not null,
	deathYear varchar default null,
	primaryProfession varchar not null,
	knownForTitles varchar not null
);

-- create title basics table
CREATE TABLE IF NOT EXISTS title_basics
(
	tconst varchar not null,
	titleType varchar not null,
	primaryTitle varchar not null,
	originalTitle varchar not null,
	isAdult boolean default false,
	startYear bigint not null,
	endYear bigint default null,
	runtimeMinutes bigint default null,
	genres varchar not null
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
	seasonNumber bigint default null,
	episodeNumber bigint default null
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