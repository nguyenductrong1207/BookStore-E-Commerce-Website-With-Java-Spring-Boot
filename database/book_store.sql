-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: book_store
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `ward` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_customer_id` bigint DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_s673mhnxcvjr5oevend5biles` (`user_customer_id`),
  CONSTRAINT `FKh2j8eo9wbeawdub7xhaom5j06` FOREIGN KEY (`user_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,NULL,NULL,NULL,NULL),(4,NULL,'',NULL,NULL,NULL),(5,NULL,'',NULL,NULL,NULL),(6,NULL,'',NULL,NULL,NULL),(7,'','','Tổ 8, Phú Thọ, Phú Chánh, thị xã Tân Uyên, Bình Dương',NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL),(13,'0','0','Tổ 8, khu phố Phú Thọ,',NULL,'0'),(14,NULL,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Đức Trọng','2024-06-03','Việt Nam 2','Quê Quán: Bình Dương\r\n',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717947030/Admin/authors/1/87770c30-cb90-45ae-82af-2d6e7ff6b056.jpg'),(2,'Anh Kiệt','2024-06-13','Việt Nam','Quê Quán: Bình Dương',NULL,NULL),(3,'Hương Duyên','2024-05-23','Việt Nam','Quê Quán: Bình Dương',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169300/Admin/authors/3/f9adbb6b-39a9-42ab-80a1-5ad6e2899f52.jpg'),(4,'Văn A','2024-05-30','Việt Nam','New Author',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717948410/Admin/authors/4/75ebc988-3ae3-4c64-a112-35d7cc8c8722.jpg'),(5,'Peter','2024-05-30','USA','History Author',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169344/Admin/authors/5/03e2b563-3fd8-4bf3-b619-bded490e529b.jpg'),(6,'Tom','2024-06-01','Italy','Author From Italy',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169364/Admin/authors/6/75bef289-f61c-4eb2-b721-010b7d95e3ba.jpg'),(7,'Ben','2024-05-03','Thai Lan','Young Author From Thai Lan',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169383/Admin/authors/7/2f062e90-64ec-418f-929c-9f0d528645ab.jpg');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `language` varchar(255) NOT NULL,
  `publication_date` varchar(255) NOT NULL,
  `num_pages` int NOT NULL,
  `sale_price` float NOT NULL,
  `book_image` varchar(255) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `publisher_id` int DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FKklnrv3weler2ftkweewlky958` (`author_id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`),
  CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`),
  CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Apprentice to the Villain','Linear algebra now rivals or surpasses calculus in importance for people working in quantitative fields of all kinds: engineers, scientists, economists and business people. Gilbert Strang has taught linear algebra at MIT for more than 50 years and the cou',15.99,210,'English','2024-06-09',352,13.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717946930/Admin/books/1/2dd06afa-af8a-4165-b51c-9072f3d6d6e9.jpg',1,3),(2,'Theory of Macroeconomic Hysteresis','This book deals with the mathematical theory of macroeconomic hysteresis, which is the theory of aggregation of microeconomic hysteresis. Microeconomic sunk cost hysteresis is usually represented by relatively simple hysteresis loops with no discrete memo',88.99,560,'English','June 20, 2022',200,82.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169035/Admin/books/2/97284a8e-772e-4efa-857a-f386075f1b8b.jpg',2,1),(3,'The Trading Game: A Confession','#1 SUNDAY TIMES BESTSELLER • A “vivid” (Financial Times) rags-to-riches memoir that takes readers inside the high-stakes drama and hubris of the trading floor, a “darkly funny” (Guardian) tale of Citibank’s one-time most profitable trader, and why he gave',19.99,320,'English','March 5, 2023',352,15.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169052/Admin/books/3/702a68fd-edfa-4f75-beab-77652a598611.jpg',3,1),(4,'Debugging','The 9 Indispensable Rules for Finding Even the Most Elusive Software and Hardware Problems. When the pressure is on to resolve an elusive software or hardware glitch, what’s needed is a cool head courtesy of a set of rules guaranteed to work on any system',12.99,130,'English','September 23, 2002',202,10.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169071/Admin/books/4/87cf07fc-8a9a-47f8-9fa3-6dc4ddf35c46.png',4,1),(5,'JavaScript from Beginner to Professional','Learn JavaScript quickly by building fun, interactive, and dynamic web apps, games, and pages. Start your journey towards becoming a JavaScript developer with the help of more than 100 fun exercises and projects',37.99,650,'English','December 15, 2021',546,32.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169096/Admin/books/5/84c4c777-261f-461e-9ac8-9c5381e3b674.png',5,1),(6,'Why We Swim','“A fascinating and beautifully written love letter to water. I was enchanted by this book.\" —Rebecca Skloot, bestselling author of The Immortal Life of Henrietta Lacks',11.99,432,'English','April 13, 2021',288,9.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169115/Admin/books/6/39af9500-9b55-47a2-913c-59effebdf53d.png',6,1),(7,'The Inheritance Games','Don\'t miss this New York Times bestselling \"impossible to put down\" (Buzzfeed) novel with deadly stakes, thrilling twists, and juicy secrets—perfect for fans of One of Us is Lying and Knives Out.',10.99,73,'English','September 1, 2020',385,8.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169129/Admin/books/7/6e2aab40-3de9-4e79-9cb7-7463ba436a61.jpg',7,1),(8,'Lost Landmarks of Orange County','Since forming in 1889, Orange County, California has become famous all over the world for being home to such popular attractions as Disneyland, Knott’s Berry Farm, and some of the most beautiful beaches in the world. But there are also many other places t',18.99,86,'English','April 16, 2020',234,16.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169142/Admin/books/8/5df7c24e-70be-4356-a719-bf3b3d96c6f9.jpg',1,1),(9,'Introduction to Linear Algebra','Linear algebra now rivals or surpasses calculus in importance for people working in quantitative fields of all kinds: engineers, scientists, economists and business people. Gilbert Strang has taught linear algebra at MIT for more than 50 years and the cou',83.99,35,'English','April 30, 2023',440,78.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169156/Admin/books/9/d162245d-0201-4323-b1ce-7cb87951c784.jpg',2,1),(10,'Silent Spring','“Rachel Carson is a pivotal figure of the twentieth century…people who thought one way before her essential 1962 book Silent Spring thought another way after it.”—Margaret Atwood',13.99,56,'English','February 1, 2022',400,10.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169178/Admin/books/10/e72f0b5a-eb80-4ceb-9b27-e6e6abedc1a4.jpg',3,1),(11,'The Lost Art of Finding Our Way','Long before GPS, Google Earth, and global transit, humans traveled vast distances using only environmental clues and simple instruments. John Huth asks what is lost when modern technology substitutes for our innate capacity to find our way. Encyclopedic i',22.99,220,'English','November 16, 2015',544,17.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169211/Admin/books/11/659ee99c-f12b-46ce-86d5-28ddbfa9335f.png',4,1),(12,'Who Was Leonardo da Vinci? ','Leonardo da Vinci was a gifted painter, talented musician, and dedicated scientist and inventor, designing flying machines, submarines, and even helicopters.  Yet he had a hard time finishing things, a problem anyone can relate to.  Only thirteen painting',40.99,20,'English','September 8, 2005',112,36.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169231/Admin/books/12/8793933e-4780-4b93-9a58-1f1332bcc36a.jpg',5,1),(13,'Harry Potter and the Sorcerer’s Stone','<div><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-size: 14px; font-style: italic;\">This article is about the book.</span></div>',20.99,100,'English','2024-06-12',500,14.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717214616/Admin/books/13/701c0290-7bb0-4ede-b15b-60767717b25d.jpg',6,3),(14,'Programming','The 9 Indispensable Rules for Finding Even the Most Elusive Software and Hardware Problems. When the pressure is on to resolve an elusive software or hardware glitch, what’s needed is a cool head courtesy of a set of rules guaranteed to work on any system',27.99,50,'English','2023-06-12',150,23.99,'http://res.cloudinary.com/dggujnlln/image/upload/v1717169035/Admin/books/2/97284a8e-772e-4efa-857a-f386075f1b8b.jpg',2,3);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_category` (
  `book_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`category_id`),
  KEY `FKam8llderp40mvbbwceqpu6l2s` (`category_id`),
  CONSTRAINT `FKam8llderp40mvbbwceqpu6l2s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FKnyegcbpvce2mnmg26h0i856fd` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (1,1),(1,2),(13,2),(13,7);
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5n0sq8ulj6ykdnrh4dnk0vfmw` (`book_id`),
  KEY `FKioh3c0mo0al2kswtnk5r09y7f` (`customer_id`),
  CONSTRAINT `FK5n0sq8ulj6ykdnrh4dnk0vfmw` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FKioh3c0mo0al2kswtnk5r09y7f` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,3,17,21),(2,2,17,12),(3,4,17,4),(4,5,17,2),(5,1,17,12),(6,10,17,3),(7,7,17,1),(8,2,18,1),(9,5,18,4),(10,11,17,2),(11,8,17,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Architecture','Architecture'),(2,'Art','Art'),(3,'Action','Action'),(4,'Biography','Biography'),(5,'Body, Mind & Spirit','Body, Mind & Spirit'),(6,'Business & Economics','Business & Economics'),(7,'Children Fiction','Children Fiction'),(8,'Children Non-Fiction','Children Non-Fiction'),(9,'Comics & Graphics','Comics & Graphics'),(10,'Cooking','Cooking'),(11,'Crafts & Hobbies','Crafts & Hobbies'),(12,'Design','Design'),(13,'Drama','Drama'),(14,'Education','Education'),(15,'Family & Relationships','Family & Relationships'),(16,'Fiction','Fiction'),(17,'Foreign Language','Foreign Language'),(18,'Games','Games'),(19,'History','History'),(20,'House & Home','House & Home');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout` (
  `checkout_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shipping_cost_total` float NOT NULL,
  `subtotal` float NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`checkout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout`
--

LOCK TABLES `checkout` WRITE;
/*!40000 ALTER TABLE `checkout` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_address`
--

DROP TABLE IF EXISTS `customer_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_address` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `ward` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_address`
--

LOCK TABLES `customer_address` WRITE;
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `address_id` int DEFAULT NULL,
  `age` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_customer_address_idx` (`address_id`),
  CONSTRAINT `fk_customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (8,'Duyen','Van','test1@gmail.com','123216978','$2a$10$qC1MM7F22RtlavgPUNFrleCMngR2uO7g7yc/UDQUMC02uJr4eMRzW',6,22,'hihihi','Giao su',NULL,_binary '\0','http://res.cloudinary.com/dggujnlln/image/upload/v1717169326/Admin/authors/4/24690ecd-3bc6-482c-a888-33fb47e551a6.jpg'),(9,'Trong','Nguyen','123123@gmail.com','123123123','$2a$10$QD3sn0YL8DJpOgxyci8XKuxJdgqJg1hk7gBaMHu45XLONKgtUinom',7,22,'hehehehe','gv',NULL,_binary '\0','http://res.cloudinary.com/dggujnlln/image/upload/v1717169383/Admin/authors/7/2f062e90-64ec-418f-929c-9f0d528645ab.jpg'),(15,'Kiet','Luong','kietluong.071002@gmail.com','3134567687','$2a$10$dJBTEzUqoTJXF4bFOcP6FumHGRF/RChCDLgbuWGyUb.85JpiuThKW',13,23,'hahaha','student','6nCmhW',_binary '','http://res.cloudinary.com/dggujnlln/image/upload/v1717167830/Admin/authors/2/5da45590-79d9-4f38-a4b2-902e6bf83e5f.jpg'),(16,'Văn Thị ','Hương Duyên','huongduyenvan3012@gmail.com','2457953243','$2a$10$pGbd6AQkl1zOpT6aRikAQeyQUt8ugrxfkDBCeHq6DbVEKJRyswMxm',14,21,'hohohoho','hiiiiii',NULL,_binary '','http://res.cloudinary.com/dggujnlln/image/upload/v1717169300/Admin/authors/3/f9adbb6b-39a9-42ab-80a1-5ad6e2899f52.jpg'),(17,'Nguyễn Đức','Trọng','trong.nguyen.cit20@eiu.edu.vn','123543632','$2a$10$n46rnXbU0y.NNKx51rYnCu49j9/XZCN5g9dlLZjHnV.1TWHFz7OYO',15,20,'huhuhuhu','heeeeee',NULL,_binary '','http://res.cloudinary.com/dggujnlln/image/upload/v1717947030/Admin/authors/1/87770c30-cb90-45ae-82af-2d6e7ff6b056.jpg'),(18,'Nguyễn Đức','Trọng','ductrong12072002@gmail.com','34534523424','$2a$10$RgeGV0ecvW1LFTfjXSVuAeDJJQ5RXQ.fkSBKPzDRtEv.qltneXZ7O',16,19,'heeee','hoooooo',NULL,_binary '','http://res.cloudinary.com/dggujnlln/image/upload/v1717169364/Admin/authors/6/75bef289-f61c-4eb2-b721-010b7d95e3ba.jpg');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `forgot_id` int NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int NOT NULL,
  `user_customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`forgot_id`),
  UNIQUE KEY `UK_6xjowsfx44x0ri90kq6pn5ivd` (`user_customer_id`),
  CONSTRAINT `FKk19sao72oor2t3kv12hwuhcvd` FOREIGN KEY (`user_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
INSERT INTO `forgot_password` VALUES (1,'2024-05-03 13:23:54.685000',576192,7);
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `author_news` varchar(255) NOT NULL,
  `description_news` text NOT NULL,
  `news_image` varchar(255) DEFAULT NULL,
  `publication` date NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `details_id` int NOT NULL AUTO_INCREMENT,
  `orders_id` int NOT NULL,
  `book_id` int DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  PRIMARY KEY (`details_id`),
  KEY `fk_order_update_idx` (`orders_id`),
  KEY `fk_details_book_idx` (`book_id`),
  CONSTRAINT `fk_details_order` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_items_id` int NOT NULL AUTO_INCREMENT,
  `product_cost` float NOT NULL,
  `quantity` int DEFAULT NULL,
  `shipping_cost` float NOT NULL,
  `subtotal` float NOT NULL,
  `unit_price` double DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `orders_id` int DEFAULT NULL,
  PRIMARY KEY (`order_items_id`),
  KEY `FKqmxbj1e77sls50umaww7pkpnx` (`orders_id`),
  KEY `FKqscqcme08spiyt2guyqdj72eh` (`book_id`),
  CONSTRAINT `FKqmxbj1e77sls50umaww7pkpnx` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`),
  CONSTRAINT `FKqscqcme08spiyt2guyqdj72eh` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,10.99,1,10,15.99,15.99,1,2),(2,8.99,1,15,10.99,10.99,7,3),(3,10.99,10,15,12.99,12.99,4,4);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `order_status` enum('CANCELLED','DELIVERED','NEW','PACKAGED','PAID','PICKED','PROCESSING','REFUNDED','RETURNED','SHIPPING') DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `payment_method` enum('COD','CREDIT_CARD') DEFAULT NULL,
  `product_cost` float NOT NULL,
  `shipping_cost` float NOT NULL,
  `subtotal` float NOT NULL,
  `total` float NOT NULL,
  `customer_id` bigint NOT NULL,
  PRIMARY KEY (`orders_id`),
  KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`),
  CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'project.bookstore.entity.Address@31d327b6','Duyen','Van','NEW','2024-05-26','CREDIT_CARD',10.99,10,25.99,15.99,8),(3,'project.bookstore.entity.Address@7cc966a9','Kiet','Luong','CANCELLED','2024-06-07','CREDIT_CARD',8.99,15,20.99,10.99,15),(4,'project.bookstore.entity.Address@440309c5','Trong','Nguyen','NEW','2024-06-07','CREDIT_CARD',10.99,20,22.99,12.99,9);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `publisher_address` varchar(255) DEFAULT NULL,
  `publisher_email` varchar(255) DEFAULT NULL,
  `publisher_name` varchar(255) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Vietnam','Tp.Ho Chi Minh','abc@gmail.com','Nha Nam'),(3,'Vietnam','Ha Noi','info@nxbkimdong.com.vn','Kim Dong');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_method`
--

DROP TABLE IF EXISTS `shipping_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_method` (
  `method_id` int NOT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_method`
--

LOCK TABLES `shipping_method` WRITE;
/*!40000 ALTER TABLE `shipping_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slider` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'It is a long established fact that a reader will be distracted by thereadable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal.','http://res.cloudinary.com/dggujnlln/image/upload/v1717896622/Admin/authors/1/948603ca-7878-4595-b61e-10ce1742222c.png','Think and Grow Rich',_binary ''),(2,'Hello girl','http://res.cloudinary.com/dggujnlln/image/upload/v1717851202/Admin/authors/2/544c82c4-90cf-4462-bb26-90a71d4c9a3a.png','My girl',_binary '\0'),(3,'\"Pushing Cloud\" is a techno-thriller that questions the boundaries of reality and invites readers to venture into a world where the tangible and  virtual converge.','http://res.cloudinary.com/dggujnlln/image/upload/v1717896703/Admin/authors/3/c5d8f9f2-83c4-406f-95d4-b0c6aff97968.png','Pushing Cloud',_binary ''),(4,'Hello miss','http://res.cloudinary.com/dggujnlln/image/upload/v1717851251/Admin/authors/4/7e88b111-68a2-4395-b56b-769045a99099.png','Pretty girl',_binary '\0');
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-10  2:28:22
