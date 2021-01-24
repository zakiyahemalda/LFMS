-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2018 at 07:58 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `workshop1`
--

-- --------------------------------------------------------

--
-- Table structure for table `compartment`
--

CREATE TABLE `compartment` (
  `compId` varchar(20) NOT NULL,
  `shelvesNo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compartment`
--

INSERT INTO `compartment` (`compId`, `shelvesNo`) VALUES
('C001', 'S7470414d-0'),
('C002', 'S7470414d-0'),
('C003', 'S7470414d-0'),
('C004', 'S7470414d-0'),
('C005', 'S7470414d-0'),
('C006', 'S7470414d-0'),
('C007', 'S9cb55cc0-5'),
('C008', 'S9cb55cc0-5'),
('C009', 'S9cb55cc0-5'),
('C010', 'S9cb55cc0-5'),
('C011', 'S9cb55cc0-5'),
('C012', 'S9cb55cc0-5');

--
-- Triggers `compartment`
--
DELIMITER $$
CREATE TRIGGER `compartment_before_insert` BEFORE INSERT ON `compartment` FOR EACH ROW BEGIN
	INSERT INTO compartment_seq VALUES(NULL);
    SET NEW.compId = CONCAT('C', LPAD(LAST_INSERT_ID(), 3, '0'));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `compartment_seq`
--

CREATE TABLE `compartment_seq` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compartment_seq`
--

INSERT INTO `compartment_seq` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12);

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE `file` (
  `fileId` varchar(20) NOT NULL,
  `fileName` varchar(20) NOT NULL,
  `regDate` date NOT NULL,
  `fileStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `file`
--

INSERT INTO `file` (`fileId`, `fileName`, `regDate`, `fileStatus`) VALUES
('F001', 'BIMB', '2018-05-16', 1),
('F002', 'CIMB', '2018-05-16', 1),
('F003', 'MAYBANK', '2018-05-16', 0),
('F004', 'HONG LEONG', '2018-05-16', 0);

--
-- Triggers `file`
--
DELIMITER $$
CREATE TRIGGER `file_before_insert` BEFORE INSERT ON `file` FOR EACH ROW BEGIN
	INSERT INTO file_seq VALUES(NULL);
    SET NEW.fileId = CONCAT('F', LPAD(LAST_INSERT_ID(), 3, '0'));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `filedistribution`
--

CREATE TABLE `filedistribution` (
  `fileDistID` varchar(20) NOT NULL,
  `compId` varchar(20) NOT NULL,
  `fileId` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `filedistribution`
--

INSERT INTO `filedistribution` (`fileDistID`, `compId`, `fileId`) VALUES
('FD001', 'C001', 'F001'),
('FD002', 'C001', 'F002');

--
-- Triggers `filedistribution`
--
DELIMITER $$
CREATE TRIGGER `filedistribution_before_insert` BEFORE INSERT ON `filedistribution` FOR EACH ROW BEGIN
	INSERT INTO filedistribution_seq VALUES(NULL);
    SET NEW.fileDistID = CONCAT('FD', LPAD(LAST_INSERT_ID(), 3, '0'));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `filedistribution_seq`
--

CREATE TABLE `filedistribution_seq` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `filedistribution_seq`
--

INSERT INTO `filedistribution_seq` (`id`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Table structure for table `file_seq`
--

CREATE TABLE `file_seq` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `file_seq`
--

INSERT INTO `file_seq` (`id`) VALUES
(1),
(2),
(3),
(4);

-- --------------------------------------------------------

--
-- Table structure for table `shelves`
--

CREATE TABLE `shelves` (
  `shelvesNo` varchar(30) NOT NULL,
  `location` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shelves`
--

INSERT INTO `shelves` (`shelvesNo`, `location`) VALUES
('S7470414d-0', 'ZONE A'),
('S9cb55cc0-5', 'ZONE B');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'salinataib', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compartment`
--
ALTER TABLE `compartment`
  ADD PRIMARY KEY (`compId`);

--
-- Indexes for table `compartment_seq`
--
ALTER TABLE `compartment_seq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`fileId`);

--
-- Indexes for table `filedistribution`
--
ALTER TABLE `filedistribution`
  ADD PRIMARY KEY (`fileDistID`);

--
-- Indexes for table `filedistribution_seq`
--
ALTER TABLE `filedistribution_seq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `file_seq`
--
ALTER TABLE `file_seq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shelves`
--
ALTER TABLE `shelves`
  ADD PRIMARY KEY (`shelvesNo`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `compartment_seq`
--
ALTER TABLE `compartment_seq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `filedistribution_seq`
--
ALTER TABLE `filedistribution_seq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `file_seq`
--
ALTER TABLE `file_seq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
