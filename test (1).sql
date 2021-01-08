-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2019 at 09:30 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `gates`
--

CREATE TABLE `gates` (
  `GATE NUMBER` text NOT NULL,
  `12:00 till 2:00 AM` text NOT NULL,
  `2:00 till 4:00 AM` text NOT NULL,
  `4:00 till 6:00 AM` text NOT NULL,
  `6:00 till 8:00 AM` text NOT NULL,
  `8:00 till 10:00 AM` text NOT NULL,
  `10:00 till 12:00 AM` text NOT NULL,
  `12:00 till 2:00 PM` text NOT NULL,
  `2:00 till 4:00 PM` text NOT NULL,
  `4:00 till 6:00 PM` text NOT NULL,
  `6:00 till 8:00 PM` text NOT NULL,
  `8:00 till 10:00 PM` text NOT NULL,
  `10:00 till 12:00 PM` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gates`
--

INSERT INTO `gates` (`GATE NUMBER`, `12:00 till 2:00 AM`, `2:00 till 4:00 AM`, `4:00 till 6:00 AM`, `6:00 till 8:00 AM`, `8:00 till 10:00 AM`, `10:00 till 12:00 AM`, `12:00 till 2:00 PM`, `2:00 till 4:00 PM`, `4:00 till 6:00 PM`, `6:00 till 8:00 PM`, `8:00 till 10:00 PM`, `10:00 till 12:00 PM`) VALUES
('1', 'booked by salah', 'booked by salah', 'booked by salah', 'booked by salah', 'free', 'booked by jad', 'free', 'free', 'booked by khalil', 'free', 'booked by khalil', 'free'),
('2', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('3', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('4', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('5', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg', 'booked by mrg'),
('6', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('7', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('8', 'free', 'free', 'free', 'booked by khalil', 'free', 'free', 'free', 'free', 'free', 'booked by khalil', 'free', 'free'),
('9', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free'),
('10', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'free', 'booked by khalil', 'free', 'free', 'free');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `Username` text NOT NULL,
  `Transaction Type` text NOT NULL,
  `GateNumber` text NOT NULL,
  `TimeSlot` text NOT NULL,
  `Date/Time` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`Username`, `Transaction Type`, `GateNumber`, `TimeSlot`, `Date/Time`) VALUES
('salah', 'reserve', '1', '12:00 till 2:00 AM', '2019/04/28  14:16:18'),
('salah', 'reserve', '1', '6:00 till 8:00 AM', '2019/04/28  14:16:41'),
('salah', 'cancelReservation', '1', '12:00 till 2:00 AM', '2019/04/28  14:17:08'),
('salah', 'cancelReservation', '1', '6:00 till 8:00 AM', '2019/04/28  14:17:17'),
('khalil', 'GateCheck', '2', 'n/a', '2019/04/28  14:18:48'),
('salah', 'reserve', '1', '12:00 till 2:00 AM', '2019/04/28  14:19:00'),
('khalil', 'GateCheck', '2', 'n/a', '2019/04/28  14:19:02'),
('salah', 'reserve', '1', '4:00 till 6:00 AM', '2019/04/28  14:19:08'),
('khalil', 'GateCheck', '2', 'n/a', '2019/04/28  14:19:12'),
('salah', 'reserve', '1', '2:00 till 4:00 AM', '2019/04/28  14:19:21'),
('khalil', 'GateCheck', '2', 'n/a', '2019/04/28  14:19:25'),
('salah', 'historyCheck', 'n/a', 'n/a', '2019/04/28  14:19:37'),
('salah', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/28  14:19:43'),
('khalil', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/28  14:19:46'),
('salah', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/28  14:19:51'),
('khalil', 'reserve', '1', '8:00 till 10:00 AM', '2019/04/28  14:20:13'),
('khalil', 'reserve', '1', '8:00 till 10:00 PM', '2019/04/28  14:20:18'),
('khalil', 'reserve', '8', '6:00 till 8:00 AM', '2019/04/28  14:20:39'),
('khalil', 'reserve', '8', '6:00 till 8:00 PM', '2019/04/28  14:20:44'),
('khalil', 'historyCheck', 'n/a', 'n/a', '2019/04/28  14:20:57'),
('khalil', 'cancelReservation', '1', '8:00 till 10:00 AM', '2019/04/28  14:21:09'),
('khalil', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/28  14:21:18'),
('khalil', 'historyCheck', 'n/a', 'n/a', '2019/04/28  14:21:21'),
('salah', 'cancelReservation', '1', '12:00 till 2:00 AM', '2019/04/28  14:27:32'),
('salah', 'GateCheck', '2', 'n/a', '2019/04/28  14:27:37'),
('salah', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/28  14:27:41'),
('salah', 'reserve', '1', '12:00 till 2:00 AM', '2019/04/28  14:30:36'),
('salah', 'reserve', '1', '6:00 till 8:00 AM', '2019/04/28  14:31:00'),
('salah', 'historyCheck', 'n/a', 'n/a', '2019/04/28  14:31:12'),
('khalil', 'reserve', '1', '4:00 till 6:00 PM', '2019/04/28  14:34:19'),
('khalil', 'reserve', '10', '4:00 till 6:00 PM', '2019/04/28  14:34:27'),
('jad', 'GateCheck', '2', 'n/a', '2019/04/29  13:27:47'),
('jad', 'reserve', '1', '8:00 till 10:00 AM', '2019/04/29  13:28:10'),
('jad', 'reserve', '5', '8:00 till 10:00 PM', '2019/04/29  13:28:19'),
('jad', 'historyCheck', 'n/a', 'n/a', '2019/04/29  13:28:25'),
('jad', 'GateCheck', '2', 'n/a', '2019/04/29  13:28:39'),
('jad', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  13:28:44'),
('jad', 'TimeSlotSearch', 'n/a', '4:00 till 6:00 AM', '2019/04/29  13:28:55'),
('jad', 'historyCheck', 'n/a', 'n/a', '2019/04/29  13:33:02'),
('jad', 'historyCheck', 'n/a', 'n/a', '2019/04/29  15:07:29'),
('jad', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  15:13:31'),
('jad', 'GateCheck', '2', 'n/a', '2019/04/29  15:13:38'),
('jad', 'GateCheck', '2', 'n/a', '2019/04/29  15:20:09'),
('jad', 'cancelReservation', '1', '8:00 till 10:00 AM', '2019/04/29  15:34:23'),
('jad', 'cancelReservation', '5', '8:00 till 10:00 PM', '2019/04/29  15:35:50'),
('jad', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  15:42:12'),
('jad', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/29  15:42:29'),
('jad', 'TimeSlotSearch', 'n/a', '6:00 till 8:00 AM', '2019/04/29  15:42:38'),
('jad', 'TimeSlotSearch', 'n/a', '6:00 till 8:00 AM', '2019/04/29  15:43:16'),
('jad', 'historyCheck', 'n/a', 'n/a', '2019/04/29  15:52:38'),
('jad', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  15:52:43'),
('jad', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/29  15:56:05'),
('jad', 'reserve', '1', '10:00 till 12:00 AM', '2019/04/29  15:59:34'),
('jad', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  16:00:53'),
('mrg', 'historyCheck', 'n/a', 'n/a', '2019/04/29  18:41:41'),
('mrg', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  18:42:28'),
('mrg', 'GateCheck', '4', 'n/a', '2019/04/29  18:49:21'),
('mrg', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/29  18:49:27'),
('mrg', 'reserve', '5', '12:00 till 2:00 AM', '2019/04/29  18:49:36'),
('mrg', 'reserve', '5', '12:00 till 2:00 PM', '2019/04/29  18:49:41'),
('mrg', 'reserve', '5', '2:00 till 4:00 PM', '2019/04/29  18:49:46'),
('mrg', 'reserve', '5', '2:00 till 4:00 AM', '2019/04/29  18:49:51'),
('mrg', 'reserve', '5', '4:00 till 6:00 AM', '2019/04/29  18:49:56'),
('mrg', 'reserve', '5', '4:00 till 6:00 PM', '2019/04/29  18:50:01'),
('mrg', 'historyCheck', 'n/a', 'n/a', '2019/04/29  21:05:05'),
('mrg', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/29  21:06:20'),
('mrg', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  21:06:56'),
('mrg', 'reserve', '5', '6:00 till 8:00 AM', '2019/04/29  21:07:48'),
('mrg', 'reserve', '5', '8:00 till 10:00 AM', '2019/04/29  21:07:53'),
('mrg', 'reserve', '5', '10:00 till 12:00 AM', '2019/04/29  21:07:59'),
('mrg', 'reserve', '5', '6:00 till 8:00 PM', '2019/04/29  21:08:09'),
('mrg', 'reserve', '5', '8:00 till 10:00 PM', '2019/04/29  21:08:15'),
('mrg', 'reserve', '5', '10:00 till 12:00 PM', '2019/04/29  21:08:25'),
('mrg', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  21:09:01'),
('nourhane', 'historyCheck', 'n/a', 'n/a', '2019/04/29  21:11:48'),
('nourhane', 'reserve', '6', '6:00 till 8:00 AM', '2019/04/29  21:12:42'),
('nourhane', 'historyCheck', 'n/a', 'n/a', '2019/04/29  21:12:46'),
('nourhane', 'cancelReservation', '6', '6:00 till 8:00 AM', '2019/04/29  21:14:11'),
('nourhane', 'TimeSlotSearch', 'n/a', '12:00 till 2:00 AM', '2019/04/29  21:16:07'),
('nourhane', 'CheckAvailableGates', 'n/a', 'n/a', '2019/04/29  21:16:15'),
('nourhane', 'GateCheck', '5', 'n/a', '2019/04/29  21:16:22'),
('nourhane', 'GateCheck', '6', 'n/a', '2019/04/29  21:16:35'),
('nourhane', 'historyCheck', 'n/a', 'n/a', '2019/04/29  21:16:51');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User` text NOT NULL,
  `Password` text NOT NULL,
  `Date/Time Registered` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User`, `Password`, `Date/Time Registered`) VALUES
('salah', '350project', '2019/04/28  14:16:03'),
('khalil', 'king1234', '2019/04/28  14:18:35'),
('jad', 'jad1234', '2019/04/29  13:27:37'),
('mrg', 'fake55', '2019/04/29  18:41:03'),
('nourhane', '350project', '2019/04/29  21:11:43');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
