
CREATE TABLE `user_info` (
                           `id` varchar(36) NOT NULL,
                           `login_name` varchar(32) NOT NULL,
                           `password` varchar(45) NOT NULL,
                           `salt` varchar(36) NOT NULL,
                           `roles` varchar(255) DEFAULT NULL,
                           `nick_name` varchar(32) DEFAULT NULL,
                           `is_del` tinyint(4) DEFAULT '1',
                           `reset_password_answer` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UNI_LOGIN_NAME` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `file_info` (
                           `id` varchar(36) NOT NULL DEFAULT '',
                           `filename` varchar(255) DEFAULT NULL,
                           `size` bigint(20) DEFAULT NULL,
                           `path` varchar(128) DEFAULT NULL,
                           `timestamp` bigint(20) DEFAULT NULL,
                           `author` int(255) DEFAULT NULL,
                           `isdel` tinyint(2) unsigned NOT NULL DEFAULT '1',
                           `thumbImagePath` varchar(255) DEFAULT NULL,
                           `suffix` varchar(10) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `IDX_FILENAME` (`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
