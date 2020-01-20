INSERT INTO Disciplines(discname) VALUES ('Discipline1');
INSERT INTO Disciplines(discname) VALUES ('Discipline2');
INSERT INTO Disciplines(discname) VALUES ('Discipline3');

INSERT INTO TournamentModes(modename) VALUES ('Mode1');
INSERT INTO TournamentModes(modename) VALUES ('Mode2');
INSERT INTO TournamentModes(modename) VALUES ('Mode3');

INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('adres1@email.com', '1111', 'Imie1', 'Nazwisko1', TRUE);
INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('bedres2@email.com', '2222', 'Imie2', 'Nazwisko2', TRUE);
INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('cedres3@email.com', '3333', 'Imie3', 'Nazwisko3', TRUE);
INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('dres4@email.com', '4444', 'Imie4', 'Nazwisko4', TRUE);
INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('abdres5@email.com', '5555', 'Imie5', 'Nazwisko5', TRUE);
INSERT INTO Users(email, passHash, firstName, lastName, active) VALUES ('123', '123', 'Tester', 'Testowy', TRUE);

INSERT INTO Teams(name, capId) VALUES ('Team1', 1);
INSERT INTO Teams(name, capId) VALUES ('Team2', 2);
INSERT INTO Teams(name, capId) VALUES ('Team3', 1);
INSERT INTO Teams(name, capId) VALUES ('Team4', 4);
INSERT INTO Teams(name, capId) VALUES ('Team5', 2);
INSERT INTO Teams(name, capId) VALUES ('Team6', 3);
INSERT INTO Teams(name, capId) VALUES ('Team7', 5);
INSERT INTO Teams(name, capId) VALUES ('Team8', 4);

INSERT INTO UsersInDisciplines(userId, discId) VALUES (1, 1);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (1, 2);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (2, 2);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (3, 1);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (3, 2);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (3, 3);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (4, 1);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (5, 1);
INSERT INTO UsersInDisciplines(userId, discId) VALUES (5, 3);

INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (1, 1);
INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (2, 2);
INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (3, 3);
INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (4, 1);
INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (5, 2);
INSERT INTO TeamsInDisciplines(teamId, discId) VALUES (6, 3);

INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (1, 1, '2020-01-20');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (2, 2, '2020-01-21');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (3, 1, '2020-01-22');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (4, 4, '2020-01-23');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (1, 3, '2020-01-24');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (5, 2, '2020-01-24');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (6, 3, '2020-01-24');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (7, 5, '2020-01-24');
INSERT INTO PlayersInTeams(teamId, userId, joinDate) VALUES (8, 4, '2020-01-24');

INSERT INTO Tournaments(name, startingDate, endingDate, adminId, modeId, discId, teamSize, finished) VALUES ('Turniej1', '2020-01-20', '2020-01-22', 1, 1, 1, 10, false);
INSERT INTO Tournaments(name, startingDate, endingDate, adminId, modeId, discId, teamSize, finished) VALUES ('Turniej2', '2020-01-21', '2020-01-23', 2, 1, 2, 10, false);
INSERT INTO Tournaments(name, startingDate, endingDate, adminId, modeId, discId, teamSize, finished) VALUES ('Turniej3', '2020-01-22', '2020-01-24', 2, 1, 3, 10, false);

INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (1, 1, '2020-01-20', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (1, 3, '2020-01-20', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (1, 4, '2020-01-20', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (1, 5, '2020-01-20', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (2, 1, '2020-01-21', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (2, 2, '2020-01-21', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (2, 3, '2020-01-21', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (3, 3, '2020-01-22', 3, false, 1);
INSERT INTO TeamsInTournaments(tourid, teamid, joindate, points, eliminated, groupnr) VALUES (3, 5, '2020-01-22', 3, false, 1);

INSERT INTO Matches(tourId, matchDate, finished, winner, team1Id, team2Id) VALUES (1, '2020-02-10', FALSE, NULL, 1, 4);
INSERT INTO Matches(tourId, matchDate, finished, winner, team1Id, team2Id) VALUES (2, '2020-02-09', FALSE, NULL, 2, 5);
INSERT INTO Matches(tourId, matchDate, finished, winner, team1Id, team2Id) VALUES (3, '2020-02-11', FALSE, NULL, 3, 6);
INSERT INTO Matches(tourId, matchDate, finished, winner, team1Id, team2Id) VALUES (1, '2020-01-10', TRUE, 1, 1, 4);
