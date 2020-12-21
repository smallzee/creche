<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 12/21/20
 * Time: 1:18 PM
 */

    session_start();
    require_once 'func.php';
    define('Env', 'onlin');

    define("DB_PREFIX", "ch_");

    define("HOME_DIR","http://projects.io/app/creche/");
    define("HTML_TEMPLATE",base_url('templates/'));
    define("USER_SESSION_HOLDER", "admin");
    define("WEB_TITLE","FPE Staff School");
    define("WEB_SUB_TITLE","CH");

    if (Env == "online") {
        define('DB_HOST', 'localhost');
        define('DB_TABLE', 'zaodlibb_main');
        define('DB_USER', 'zaodlibb_main');
        define('DB_PASSWORD', '8#&c}}N.#k5x');
    }else{
        define('DB_HOST', 'localhost');
        define('DB_TABLE', 'app_fpe_creche');
        define('DB_USER', 'root');
        define('DB_PASSWORD', '');
    }

    try {
        $db = new PDO('mysql:host='.DB_HOST.';dbname='.DB_TABLE, DB_USER, DB_PASSWORD, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
        $db->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_WARNING );
    }

    catch (PDOException $e){
        die('<br/><center><font size="15">Could not connect with database</font></center>');
    }