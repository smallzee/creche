<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 1/19/21
 * Time: 8:45 AM
 */

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, GET, PUT, OPTIONS, PATCH, DELETE');
require_once 'config/core.php';

$data = $error = $ch_data = array();
$post_data = $_POST;

if (isset($_POST['login'])){
    $parent_id = $post_data['parent_id'];
    $password = $post_data['password'];

    $sql = $db->query("SELECT * FROM ".DB_PREFIX."parents WHERE parent_id='$parent_id' and password='$password'");
    $rs = $sql->fetch(PDO::FETCH_ASSOC);

    $parent_id2 = $rs['id'];
    $children = $db->query("SELECT * FROM ".DB_PREFIX."students WHERE parent_id='$parent_id2'");

    if ($sql->rowCount() == 0){
        $data['error'] = 0;
        $data['msg'] = "Invalid login details";
    }else{
        $data['error'] = 1;
        while ($children_data = $children->fetch(PDO::FETCH_ASSOC)){
            $ch_data[] = $children_data;
        }
    }

    $info = array('status'=>$data,'info'=>$rs,'children'=>$ch_data);

    echo json_encode($info);
    exit();
}