CREATE TABLE `file` (
  `f_uuid` varchar(32) NOT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `order_uuid` varchar(32) NOT NULL,
  `u_uuid` varchar(32) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `phone_Num` varchar(20) DEFAULT NULL,
  `done_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `color` tinyint(4) DEFAULT NULL,
  `duplex` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `u_auth` (
  `u_uuid` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`u_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `u_info` (
  `uuid` varchar(32) NOT NULL,
  `nickName` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_Num` varchar(20) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;