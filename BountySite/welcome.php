<?php
$servername = 'localhost'; //php mysql login info here
$username = 'username'; 
$password = 'password';

//creates PHP connection
$conn = new mysqli($servername, $username, $password);

if($conn->connect_error){
    die("Connection failed: " . $conn->connection_error);

}

echo "Connected Successfully!";

$sql = "create table RESTAURANTS(
	RestaurantID varchar(9),
	RestaurantName varchar(100),
	RestaurantOwner varchar(50),
	RestaurantAddress varchar(30),
    RestaurantPhoneNumber varchar(50),
	RestaurantBusinessHours varchar(30),
	DeliveryInfor varchar(30),
	constraint pk_restaurants primary key (RestuarantID)
);"; //SQL created by lauren

if (mysqli_query($conn, $sql)){
    echo "Table RESTAURANTS created successfully";
}else{
    echo "Error creating database: " . mysqli_error($conn);
}

mysqli_close($conn);
?>