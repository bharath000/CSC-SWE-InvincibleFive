<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="bountyStyle.css">
    <title>New User Registration</title>
</head>

<style>
    .buttons {
    background-color: white;
    color: black;
    border: 2px solid rgb(115, 185, 133);
    border-radius: 0px;
    height: 80px;
    width: 250px;
    padding: 10px 15px 10px 15px;
    font-size: medium;
    }

.buttons:hover, .existingUserBtn:hover{
    background-color: rgb(157, 250, 180);
    transition-duration: 0.9s;
}

.existingUserBtn{
    background-color: white;
    color: black;
    border: 3px solid rgb(157, 250, 180);
    border-radius: 4px;
    height: 40px;
    width: 160px;
    padding: 10px 15px 10px 15px;
    font-size: medium;
}

body {
    font-family: Arial, Helvetica, sans-serif;
}
</style>
<body>

    <br style=“height:900px”;><br>
    <img div class="gallery" src="deliveryStock.jpg" alt="stock1" style="display:block; margin-left:auto; margin-right:auto; width:50%;"> 
    <h2 align="center">Register as a: </h2>

    <div id ="content" align="center">
        <button class="buttons" name="charityBtn" onclick="showCharity()">Charity</button>
        <button class="buttons" name="restaurantBtn" onclick="showRestaurant()">Restaurant</button>
        <button class="buttons" name="volunteerBtn" onclick="showVolunteer()">Volunteer</button>
    </div>

    <br style=“height:200px”;><br>
    <div id="Existing" align="center">
        <button class="existingUserBtn" name="existingUserBtn"><a href="logIn.html">Existing User?</a></button>
    </div>

    <br style=“height:900px”;><br>

    <div id="Volunteer" align="center" style="display:none;">
      <h3>Volunteer Registration</h3>
        <form action="welcome.php">
            <label for="fname">First Name: </label>
            <input type="text" id="fName" name="fName"><br><br>
            <label for="lName">Last Name: </label>
            <input type="text" id="lName" name="lName"><br><br>
            <label for="email">Email address: </label>
            <input type="email" id="email" name="email"><br><br>
            <label for="DLnum">Driver's License Number: </label>
            <input type="text" id="DLnum" name="DLnum">
            <br style=“height:200px”;><br>
            <input type="submit" value="Submit">
            <input type="reset">
        </form>
    </div>
    <br style=“height:900px”;><br>

    <div id="Restaurant" align="center" style="display:none;">
      <h3>Restaurant Registration</h3>
        <form action="welcome.php">
            <label for="rName">Restaurant Name: </label>
            <input type="text" id="rName" name="rName"><br><br>
            <label for="rAddress">Address Line 1: </label>
            <input type="text" id="rAddress" name="rAddress"><br><br>
            <label for="rAddress">Address Line 2: </label>
            <input type="text" id="rAddress2" name="rAddress2"><br><br>
            <label for="cityName">City: </label>
            <input type="text" id="cityName" name="cityName"><br><br>
            <label for="state">State: </label>
            <input type="text" id="state" name="state">
            <br style=“height:200px”;><br>
            <input type="submit" value="Submit">
            <input type="reset">
        </form>
    </div>

    <br style=“height:900px”;><br>

    <div id="Charity" align="center" style="display:none;">
      <h3>Charity Registration</h3>
        <form action="welcome.php">
            <label for="cName">Charity Name: </label>
            <input type="text" id="cName" name="cName"><br><br>
            <label for="cAddress">Address: </label>
            <input type="text" id="cName" name="cName"><br><br>
            <label for="cAddress2">Address Line 2: </label>
            <input type="text" id="cAddress2" name="cAddress2"><br><br>
            <label for="charityCity">City: </label>
            <input type="text" id="charityCity" name="charityCity"><br><br>
            <label for="cState">State: </label>
            <input type="text" id="cState" name="cState">
            <br style=“height:200px”;><br>
            <input type="submit" value="Submit">
            <input type="reset">
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>

    <script type="text/javascript">

      
          function showCharity() {
              var x = document.getElementById("Charity");
              if (x.style.display === "none") {
                x.style.display = "block";
              } else {
                x.style.display = "none";
              }
            }
      

      
            function showRestaurant() {
              var x = document.getElementById("Restaurant");
              if (x.style.display === "none") {
                x.style.display = "block";
              } else {
                x.style.display = "none";
              }
            }
      

     
          function showVolunteer() {
            var x = document.getElementById("Volunteer");
            if (x.style.display === "none") {
              x.style.display = "block";
            } else {
              x.style.display = "none";
            }
          }
        

    </script>
</body>
</html>

