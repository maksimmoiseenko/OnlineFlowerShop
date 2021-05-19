  To start you need:
change database properties to your properties;
change spring.jpa.hibernate.ddl-auto=validate to spring.jpa.hibernate.ddl-auto=create;
  After first start you need to change back spring.jpa.hibernate.ddl-auto=create to spring.jpa.hibernate.ddl-auto=validate;
  sign up 2 accounts. go to the DB and change role of first account from USER to ADMIN;
  only one and first account will be ADMIN
  else all accounts will be USER
  changing of data of ADMIN is forbidden;
  ADMIN can:
    add flowers;
    update flowers;
    delete flowers;
    add orders;
    look all orders;
    look all users;
    update all orders;
    delete all orders;
    delete all users;
    update all users;
  USER can:
    add orders;
    update own orders;
    delete own orders;
    delete myself;
    update porfile data;
    look own orders;
  
    
