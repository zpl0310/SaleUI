
About GUI:

1. it will not load a menu at start, the "replace with actual menu item" button can load selected .txt files 
2. added item are shown in the table on the right,left clicked the line on the table will pop out delete item option.   
3. click "place order" button will show a message on System about the order price and number of items ordered.
4. click "cancel order" button will remove any order listed on the table.
5. click "pay" button will pop out a optionpane ask about the information of the credit card. But only card number is 
   needed to determine which type of card it is. The credit card type will be shown if the card number is a valid one.
   Also, if the card number entered is against ISO 2894/ANSI 4.13 rule or too long or too short, an error message will
   be shown.

   
About Design Pattern:
1. Factory method is used to create the appropriate credit card class once a card number is entered. It can make the code
   easier to extend or change. The method of deciding which class to instantiate is separated so it is also a better
   encapsulation.   
2. Stradegy method is used to call the specific validation methods for each card type (not sure whether it is nessesary). 
   The validation method for each card type should be different, so by using stradegy methods allows us to plug in the 
   appropriate validation algorithms at runtime. It is also easier for adding more credit card type later.
