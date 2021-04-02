CREATE TABLE github_user_basic_info (
                                        uid varchar(20) NOT NULL,
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

alter table github_user_basic_info alter column avatar_url type varchar(150);

alter table github_user_basic_info alter column "location" type varchar(50);

alter table github_user_basic_info alter column created_at type varchar(50);

alter table github_user_basic_info alter column updated_at type varchar(50);

alter table github_user_basic_info alter column uid type TEXT;

alter table github_user_basic_info add primary key (uid);

commit;

CREATE TABLE github_user_repo (

        uid varchar(20) primary key,
        repoName varchar(50),
        user_ID varchar(20),
        gid integer,
        commits integer ,
        pulls integer,
        contributors integer,
        size integer,
        forks integer,
        stargazers_count integer,
        watchers integer,
        open_issues integer,
        foreign key (user_ID) REFERENCES  github_user_basic_info(uid)
);

CREATE TABLE github_user_repo_code(
        urcid integer primary key,
        urid integer,
        uid integer,
        gid integer,
        programming_language varchar(25),
        lines_of_code_repo integer,
        comment_repo varchar(50),
        file_size integer,
        files_by_language varchar(50),
        code_language varchar(20),
        foreign key (urid) REFERENCES  github_user_repo(urid)
);