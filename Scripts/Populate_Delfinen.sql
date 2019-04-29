INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS) VALUES ('Mads Brandt', 25, 1600, 1, 0); -- Senior member, competition swimmer
INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS) VALUES ('Alexander Sarson', 29, 1600, 1, 0); -- Motionist
INSERT INTO MEMBERS (MEMBER_NAME, AGE, ACTIVE, ARREARS) VALUES ('Benjamin Paepke', 27, 1, 0); -- Coach
INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS) VALUES ('Tobias Boldt-Jørgensen', 15, 1000, 1, 0); -- Junior member

-- Insert Mads as a competition swimmer
INSERT INTO COMPETITION_SWIMMERS (DISCIPLINES, COACH_ID, MEMBER_ID) VALUES ('Crawl', 3, 1);

-- Insert Mads' training results
INSERT INTO TRAINING_RESULTS (DISCIPLINE, TRAINING_DATE, BEST_TIME, MEMBER_ID) VALUES ('Crawl', '2019-03-22', '00:02:03', 1);

-- Insert competition which Mads won.. Of course..
INSERT INTO COMPETITION_RESULTS (EVENT_NAME, DISCIPLINE, EVENT_DATE, BEST_TIME, PLACEMENT, MEMBER_ID) VALUES ('DM', 'Crawl', '2019-04-01', '00:01:54', 1, 1);

SELECT * FROM COMPETITION_RESULTS;