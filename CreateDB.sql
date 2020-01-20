CREATE TABLE Users (
	userId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	email VARCHAR(63) NOT NULL UNIQUE,
	passHash CHAR(64),
	firstName VARCHAR(31),
	lastName VARCHAR(31),
	active BOOLEAN
);

CREATE TABLE Teams (
	teamId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR(63) UNIQUE,
	capId INTEGER,
	CONSTRAINT Teams_capId_fk FOREIGN KEY (capId) REFERENCES Users(userId)
);

CREATE TABLE Disciplines (
	discId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	discName VARCHAR(31) UNIQUE
);

CREATE TABLE TournamentModes (
	modeId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	modeName VARCHAR(31) UNIQUE
);

CREATE TABLE Tournaments (
	tourId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR(63) UNIQUE,
	startingDate DATE,
	endingDate DATE,
	adminId INTEGER NOT NULL,
	modeId INTEGER,
	discId INTEGER,
	TeamSize INTEGER,
	finished BOOLEAN,
	CONSTRAINT Tournaments_adminId_fk FOREIGN KEY (adminId) REFERENCES Users(userId),
	CONSTRAINT Tournaments_modeId_fk FOREIGN KEY (modeId) REFERENCES TournamentModes(modeId),
	CONSTRAINT Tournaments_discId_fk FOREIGN KEY (discId) REFERENCES Disciplines(discId)
);

CREATE TABLE Matches (
	matchId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	tourId INTEGER NOT NULL,
	matchDate DATE,
	finished BOOLEAN,
	winner INTEGER,
	team1Id INTEGER,
	team2Id INTEGER,
	CONSTRAINT Matches_tourId_fk FOREIGN KEY (tourId) REFERENCES Tournaments(tourId),
	CONSTRAINT Matches_winner_fk FOREIGN KEY (winner) REFERENCES Teams(teamId),
	CONSTRAINT Matches_Team1Id_fk FOREIGN KEY (team1Id) REFERENCES Teams(teamId),
	CONSTRAINT Matches_Team2Id_fk FOREIGN KEY (team2Id) REFERENCES Teams(teamId)
);

CREATE TABLE PlayersInTeams (
	teamId INTEGER NOT NULL,
	userId INTEGER NOT NULL,
	joinDate DATE,
	CONSTRAINT PlayersInTeams_pk PRIMARY KEY (teamId, userId),
	CONSTRAINT PlayersInTeams_teamId_fk FOREIGN KEY (teamId) REFERENCES Teams(teamId),
	CONSTRAINT PlayersInTeams_userId_fk FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE TeamsInTournaments (
	tourId INTEGER NOT NULL,
	teamId INTEGER NOT NULL,
	joinDate DATE,
	points SMALLINT,
	eliminated BOOLEAN,
	groupNr SMALLINT,
	CONSTRAINT TeamsInTournaments_pk PRIMARY KEY (tourId, teamId),
	CONSTRAINT TeamsInTournaments_tourId_fk FOREIGN KEY (tourId) REFERENCES Tournaments(tourId),
	CONSTRAINT TeamsInTournaments_teamId_fk FOREIGN KEY (teamId) REFERENCES Teams(teamId)
);

CREATE TABLE UsersInDisciplines (
	userId INTEGER NOT NULL,
	discId INTEGER NOT NULL,
	CONSTRAINT UsersInDisciplines_pk PRIMARY KEY (userId, discId),
	CONSTRAINT UsersInDisciplines_userId_fk FOREIGN KEY (userId) REFERENCES Users(userId),
	CONSTRAINT UsersInDisciplines_discId_fk FOREIGN KEY (discId) REFERENCES Disciplines(discId)
);

CREATE TABLE TeamsInDisciplines (
	teamId INTEGER NOT NULL,
	discId INTEGER NOT NULL,
	CONSTRAINT TeamsInDisciplines_pk PRIMARY KEY (teamId, discId),
	CONSTRAINT TeamsInDisciplines_teamId_fk FOREIGN KEY (teamId) REFERENCES Teams(teamId),
	CONSTRAINT TeamsInDisciplines_discId_fk FOREIGN KEY (discId) REFERENCES Disciplines(discId)
);
