CREATE TABLE github_user_basic_info (
                                        uid integer NOT NULL,
                                        username varchar(20) NOT NULL,
                                        "name" varchar(30) NULL,
                                        gid integer NOT NULL,
                                        avatar_url varchar(50) NULL,
                                        followers integer NULL,
                                        "following" integer NULL,
                                        "location" varchar(20) NULL,
                                        created_at varchar(15) NULL,
                                        updated_at varchar(15) NULL,
                                        public_repos integer NULL,
                                        public_gists integer NULL
);

insert into github_user_basic_info(uid, username, gid, name, avatar_url, followers, following, location, created_at, updated_at, public_repos, public_gists)
values (2, 'sdgfbhdhrg', 2, 'qwerty', 'abc.com/sahfdsja', 23, 443, 'Mumbai', '8/3/3', '3/2/4', 2, 4);