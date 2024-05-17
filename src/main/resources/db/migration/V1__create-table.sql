CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cep` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`id`, `cpf`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE);
  
  CREATE TABLE `bancos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  CREATE TABLE `pai` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id`,`email` ASC) VISIBLE);


  
  INSERT INTO `pai` (`nome`,`email`) VALUES ('Jose Silva', 'jose.silva@email.com.br');
  INSERT INTO `pai` (`nome`,`email`) VALUES ('João Santos', 'joao.santos@email.com.br');  
  INSERT INTO `pai` (`nome`,`email`) VALUES ('Pedro Lima', 'pedro.lima@email.com.br');
  
  CREATE TABLE `filho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `idade` INT NULL,
  `pai_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pai_id`
    FOREIGN KEY (`pai_id`)
    REFERENCES `pai` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('Jose Silva Filho',10, 1);  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('Jose Silva Junior',15, 1);
  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('João Santos Filho',17, 2);  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('João Santos Junior',20, 2);
  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('Pedro Lima Filho',2, 3);  
  INSERT INTO `filho` (`nome`,`idade`,`pai_id`) VALUES ('Pedro Lima Junior',3, 3);
    
  INSERT INTO `bancos` (`nome`) VALUES ('Bradesco');
  INSERT INTO `bancos` (`nome`) VALUES ('Itaú');
  INSERT INTO `bancos` (`nome`) VALUES ('Nubank');
  INSERT INTO `bancos` (`nome`) VALUES ('C6');
  INSERT INTO `bancos` (`nome`) VALUES ('Santander');
  INSERT INTO `bancos` (`nome`) VALUES ('PagBank');
  INSERT INTO `bancos` (`nome`) VALUES ('Banco do Brasil');
  INSERT INTO `bancos` (`nome`) VALUES ('Caixa Economica Federal');