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
    $parent_info = $sql->fetch(PDO::FETCH_ASSOC);

    $parent_id2 = $parent_info['id'];
    $children = $db->query("SELECT s.*, c.name as class_name FROM ".DB_PREFIX."students
     s LEFT JOIN ".DB_PREFIX."class c 
        ON s.class_id = c.id
     WHERE s.parent_id='$parent_id2'");

//    $parent_info['image'] = image_url($parent_info['image']);

    if ($sql->rowCount() == 0){
        $data['error'] = 0;
        $data['msg'] = "Invalid login details";
    }else{
        $data['error'] = 1;
        while ($children_data = $children->fetch(PDO::FETCH_ASSOC)){
            $ch_data[] = array(
                'image'=>image_url($children_data['image']),
                'fname'=>$children_data['fname'],
                'class_name'=>$children_data['class_name'],
                'term'=>term($children_data['term']),
                'academic_session'=>$children_data['academic_session'],
                'gender'=>$children_data['gender'],
                'birth'=>$children_data['birth']
            );
        }
    }

    $info = array(
        'status'=>$data,
        'parent_data'=>$parent_info,
        'children_data'=>$ch_data
    );

    echo json_encode($info);
    exit();
}