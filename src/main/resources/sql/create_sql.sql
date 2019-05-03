CREATE TABLE `TB_USER` (
  `USER_UID` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `fst_reg_dt` datetime NOT NULL,
  `fst_reg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lst_chg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lst_chg_dt` datetime NOT NULL,
  `USER_DIV` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_nm` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_pw` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`USER_UID`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `TB_ROLE` (
  `ROLE_UID` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `fst_reg_dt` datetime NOT NULL,
  `fst_reg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lst_chg_dt` datetime NOT NULL,
  `lst_chg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_desc` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `role_nm` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ROLE_UID`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `TB_MNU` (
  `MNU_UID` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `fst_reg_dt` datetime NOT NULL,
  `fst_reg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lst_chg_dt` datetime NOT NULL,
  `lst_chg_id` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mnu_ctnt` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mnu_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `mnu_nm` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mnu_sort` int(11) DEFAULT NULL,
  `mnu_style` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MNU_UID`),
  UNIQUE KEY `mnu_id` (`mnu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;