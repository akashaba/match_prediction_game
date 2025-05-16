-- Insert Sports
INSERT INTO SPORT (SPORT_NAME) VALUES
    ('Soccer');

-- Insert Divisions
INSERT INTO DIVISION (DIVSION_NAME, SPORT_ID) VALUES
    ('Premier League', 1);

-- Insert Leagues
INSERT INTO LEAGUE (LEAGUE_NAME, DIVSION_ID, LEAGUE_CODE) VALUES
                                                                        ('Fantasy League A', 1, 'ABC123'),
                                                                        ('Fantasy League B', 1, 'XYZ789');

-- Insert Users
INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, ROLE) VALUES
                                                                      ('John', 'Doe', 'johndoe', 'USER'),
                                                                      ('Jane', 'Smith', 'janesmith', 'USER');

-- Insert User-League Relationships
INSERT INTO USER_LEAGUE (USERID, LEAGID) VALUES
                                             (1, 1), -- John in League A
                                             (1, 2), -- John in League B
                                             (2, 1); -- Jane in League A

-- Insert Teams
INSERT INTO TEAMS (TEAM_NAME, DIVSION_ID) VALUES
                                                      ('Team A', 1),
                                                      ('Team B', 1),
                                                      ('Team C', 1),
                                                      ('Team D', 1);

-- Insert MatchDay
INSERT INTO MATCHDAY (MATCH_DAY, MATCH_DAY_START, MATCH_DAY_END) VALUES
    (1, '2025-05-01 10:00:00', '2025-05-01 18:00:00');

-- Insert Matches
INSERT INTO MATCHES (HOME_TEAM_ID, AWAY_TEAM_ID, MATCHDAYID) VALUES
                                                                          (1, 2, 1), -- Team A vs Team B
                                                                          (3, 4, 1), -- Team C vs Team D
                                                                          (1, 3, 1); -- Team A vs Team C

-- Insert Predictions
INSERT INTO PREDICTIONS (MATCHID, HOME, AWAY, USERID, LEAGUEID) VALUES
                                                                            (1, 3, 1, 1, 1), -- John predicts Team A 3-1 Team B (League A)
                                                                            (1, 2, 0, 2, 1), -- Jane predicts Team A 2-0 Team B (League A)
                                                                            (2, 1, 1, 1, 1), -- John predicts Team C 1-1 Team D (League A)
                                                                            (3, 0, 2, 1, 2); -- John predicts Team A 0-2 Team C (League B)

-- Insert Results
INSERT INTO RESULTS ( MATCHID, HOME, AWAY) VALUES
                                                     (1, 3, 1), -- Team A 3-1 Team B (exact match for John, correct outcome for Jane)
                                                     (2, 1, 1), -- Team C 1-1 Team D (exact draw for John)
                                                     (3, 2, 0); -- Team A 2-0 Team C (incorrect for John)

-- Insert Initial User League Points (updated by ResultsService)
INSERT INTO USER_LEAGUE_POINTS (USERID, LEAGID, POINTS) VALUES
                                                                (1, 1, 0), -- John in League A
                                                                (1, 2, 0), -- John in League B
                                                                (2, 1, 0); -- Jane in League A