<?php

// Define some constants
define( "RECIPIENT_NAME", "YOUR NAME" );
define( "RECIPIENT_EMAIL", "YOUR MAIL" );
define( "EMAIL_SUBJECT", "Visitor Message" );

// Read the form values
$success = false;
$senderName = isset( $_POST['senderName'] ) ? preg_replace( "/[^\.\-\' a-zA-Z0-9]/", "", $_POST['senderName'] ) : "";
$senderEmail = isset( $_POST['senderEmail'] ) ? preg_replace( "/[^\.\-\_\@a-zA-Z0-9]/", "", $_POST['senderEmail'] ) : "";
$senderPhone = isset( $_POST['senderPhone'] ) ? preg_replace( "/[^\.\-\' a-zA-Z0-9]/", "", $_POST['senderPhone'] ) : "";
$senderDate = isset( $_POST['senderDate'] ) ? preg_replace( "/[^\.\-\_\@a-zA-Z0-9]/", "", $_POST['senderDate'] ) : "";
$senderNumber = isset( $_POST['senderNumber'] ) ? preg_replace( "/[^\.\-\_\@a-zA-Z0-9]/", "", $_POST['senderNumber'] ) : "";
$message = isset( $_POST['message'] ) ? preg_replace( "/(From:|To:|BCC:|CC:|Subject:|Content-Type:)/", "", $_POST['message'] ) : "";

$messagecontent = "Name:" . $senderName . "\r\n Email:" . $senderEmail . "\r\n Phone:" . $senderPhone . "\r\n Date:" . $senderDate . "\r\n No of people:" . $senderNumber . "\r\n Message:" . $message;

$SpamErrorMessage = "No Websites URLs permitted";
if(preg_match("/http/i", "$senderName")) {echo "$SpamErrorMessage"; exit();}
if(preg_match("/http/i", "$senderEmail")) {echo "$SpamErrorMessage"; exit();}
if(preg_match("/http/i", "$message")) {echo "$SpamErrorMessage"; exit();}
if(preg_match("/http/i", "$senderPhone")) {echo "$SpamErrorMessage"; exit();}
if(preg_match("/http/i", "$senderDate")) {echo "$SpamErrorMessage"; exit();}
if(preg_match("/http/i", "$senderNumber")) {echo "$SpamErrorMessage"; exit();}

$recipient = RECIPIENT_NAME . " <" . RECIPIENT_EMAIL . ">";
$headers = "From: " . $senderName . " <" . $senderEmail . ">";
$success = mail( $recipient, EMAIL_SUBJECT, $messagecontent, $headers );
?>
<html>
  <head>
    <title>Thanks!</title>
    <link href="css/contactform.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <div class="contact-form-container">
  <?php if ( $success ) echo "<p>Thanks for sending your message! We'll get back to you shortly.</p>" ?>
  <?php if ( !$success ) echo "<p>There was a problem sending your message. Please try again.</p>" ?>
  <p>Click your browser's Back button to return to the page.</p>
  </div>
  </body>
</html>


