<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 1/29/21
 * Time: 1:18 PM
 */
require_once 'config/core.php';

$student_id = $_GET['student-id'];
if (!isset($student_id) or empty($student_id)){
    redirect(base_url('404.php'));
    return;
}

$sql = $db->query("SELECT * FROM ".DB_PREFIX."students WHERE id='$student_id'");

if ($sql->rowCount() == 0){
    redirect(base_url('404.php'));
    return;
}

$data = $sql->fetch(PDO::FETCH_ASSOC);

$page_title = ucwords($data['fname'])." - Dashboard";

if (isset($_POST['add'])){
    $amount = $_POST['amount'];
    $term = $_POST['term'];
    $academic_session = $_POST['session'];
    $type = $_POST['type'];
    $class_id = $data['class_id'];
    $ref = uniqid();

    $sql_check = $db->query("SELECT * FROM ".DB_PREFIX."payment WHERE student_id='$student_id' and term_id='$term' and academic_session='$academic_session'");
    if ($sql_check->rowCount() >= 1){
        $error[] = ucwords($data['fname'])." has already paid for his/her ".term($data['term'])."  school fee";
    }

    $error_count = count($error);
    if ($error_count == 0){

        $db->query("INSERT INTO ".DB_PREFIX."payment (student_id,amount,class_id,term_id,academic_session,payment_type,ref)
        VALUES('$student_id','$amount','$class_id','$term','$academic_session','$type','$ref')");

        set_flash(ucwords($data['fname']).' has been paid successful','info');

    }else{
        $msg = ($error_count == 1) ? 'An error occurred' : 'Some error(s) occurred';
        foreach ($error as $value){
            $msg.='<p>'.$value.'</p>';
        }

        set_flash($msg,'danger');
    }
}

if(isset($_POST['add-attendance'])){
    $attendance = $_POST['attendance'];
    $guardian_name = $_POST['name'];
    $guardian_phone = $_POST['phone'];

    if (!is_numeric($guardian_phone) or strlen($guardian_phone) != 11){
        $error[] = "Invalid phone number entered, it should not exceed 11 digit number ";
    }

    if (strlen($guardian_name) < 5 or strlen($guardian_name) > 100){
        $error[] = "Guardian name should be between 5 - 100 characters";
    }

    $attendance_date = date('Y-m-d H:i:s');

    $error_count = count($error);
    if ($error_count == 0){

        $db->query("INSERT INTO ".DB_PREFIX."attendance (attendance,name,phone,attendance_date,student_id)
        VALUES('$attendance','$guardian_name','$guardian_phone','$attendance_date','$student_id')");

        set_flash("Attendance has been saved successfully","info");

    }else{
        $msg = ($error_count == 1) ? 'An error occurred' : 'Some error(s) occurred';
        foreach ($error as $value){
            $msg.='<p>'.$value.'</p>';
        }
        set_flash($msg,'danger');
    }
}

require_once 'libs/head.php';
?>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add Payment</h4>
            </div>
            <div class="modal-body">

                <form action="" method="post">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label for="">School Fee Amount</label>
                                <input type="number" class="form-control" required placeholder="School Fee Amount" name="amount" readonly value="<?= student_class($data['class_id'],'school_fee') ?>" id="">
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Term</label>
                                <select name="term" class="form-control" required id="">
                                    <option value="<?= $data['term'] ?>"><?= term($data['term']) ?></option>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Academic Session</label>
                                <select name="session" class="form-control" required id="">
                                    <?php
                                    foreach (range(2021,date('Y')) as $value){
                                        $start = $value-1;
                                        ?>
                                        <option value="<?= $start.'-'.$value ?>"><?= $start.'-'.$value ?></option>
                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-12">
                            <div class="form-group">
                                <label for="">Payment Type</label>
                                <select name="type" class="form-control" required id="">
                                    <option value="" disabled selected>Select</option>
                                    <option value="2">Direct Payment</option>
                                    <option value="1">Deduct from staff salary</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" name="add" value="Submit" id="">
                    </div>
                </form>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade" id="modal-default2">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add Attendance</h4>
            </div>
            <div class="modal-body">

                <form action="" method="post">

                    <div class="form-group">
                        <label for="">Attendance</label>
                        <select name="attendance" class="form-control" required id="">
                            <option value="" disabled selected>Select</option>
                            <?php
                            foreach (array('morning','afternoon','evening') as $value){
                                ?>
                                <option value="<?= $value ?>"> <?= ucwords($value) ?></option>
                                <?php
                            }
                            ?>
                        </select>
                    </div>

                    <h6 class="page-header">Children Guardian/Parent</h6>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Name</label>
                                <input type="text" value="<?= parent_info($data['parent_id'],'fname') ?>" class="form-control" name="name" required placeholder="Name" id="">
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Phone Number</label>
                                <input type="text" class="form-control" value="<?= parent_info($data['parent_id'],'phone') ?>" name="phone" required placeholder="Phone Number" id="">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" name="add-attendance" class="btn btn-primary" value="Submit" id="">
                    </div>
                </form>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<section class="content">
    <div class="row">

        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">

            <?php flash(); ?>

            <div class="box box-widget widget-user">
                <!-- Add the bg color to the header using any of the bg-* classes -->
                <div class="widget-user-header bg-blue-gradient">
                    <h3 class="widget-user-username"><?= ucwords($data['fname']) ?></h3>
                    <h5 class="widget-user-desc">Class : <?= student_class($data['class_id'],'name') ?></h5>
                </div>
                <div class="widget-user-image">
                    <img class="img-circle" src="<?= image_url($data['image']) ?>" style="width: 80px; height: 80px;" alt="User Avatar">
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-sm-4 border-right">
                            <div class="description-block">
                                <h5 class="description-header"><?= ucwords($data['gender']) ?></h5>
                                <span class="description-text">Gender</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <!-- /.col -->
                        <div class="col-sm-4 border-right">
                            <div class="description-block">
                                <h5 class="description-header"><?= $data['birth'] ?></h5>
                                <span class="description-text">Date Of Birth</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <!-- /.col -->
                        <div class="col-sm-4">
                            <div class="description-block">
                                <h5 class="description-header"><?= parent_info($data['parent_id'],'fname')  ?></h5>
                                <span class="description-text">Parent Name</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
            </div>
            <!-- /.widget-user -->
        </div>

        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab_1" data-toggle="tab">Student Details</a></li>
                    <li><a href="#tab_2" data-toggle="tab">Payment History</a></li>
                    <li><a href="#tab_3" data-toggle="tab">Attendance</a></li>
                    <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab_1">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <tr>
                                    <td>Application Id</td>
                                    <td><?= $data['application_id'] ?></td>
                                </tr>
                                <tr>
                                    <td>Student Name</td>
                                    <td><?= $data['fname'] ?></td>
                                </tr>
                                <tr>
                                    <td>Class Name</td>
                                    <td><?= student_class($data['class_id'],'name') ?></td>
                                </tr>
                                <tr>
                                    <td>Term</td>
                                    <td><?= term($data['term']) ?></td>
                                </tr>
                                <tr>
                                    <td>Academic Session</td>
                                    <td><?= $data['academic_session'] ?></td>
                                </tr>
                                <tr>
                                    <td>Gender</td>
                                    <td><?= ucwords($data['gender']) ?></td>
                                </tr>
                            </table>

                            <h6 class="page-header">Parent Information</h6>
                            <table class="table table-bordered">
                                <tr>
                                    <td>Full Name</td>
                                    <td><?= parent_info($data['parent_id'],'fname') ?></td>
                                </tr>
                                <tr>
                                    <td>Gender</td>
                                    <td><?= parent_info($data['parent_id'],'gender') ?></td>
                                </tr>
                                <tr>
                                    <td>Phone Number</td>
                                    <td><?= parent_info($data['parent_id'],'phone') ?></td>
                                </tr>
                                <tr>
                                    <td>Email Address</td>
                                    <td><?= parent_info($data['parent_id'],'email') ?></td>
                                </tr>
                                <tr>
                                    <td>Occupation</td>
                                    <td><?= parent_info($data['parent_id'],'occupation') ?></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="tab_2">

                        <a href="" class="btn btn-primary" data-toggle="modal" data-target="#modal-default" style="margin-bottom: 20px;">Add Payment</a>

                        <div class="table-responsive">
                            <table class="table table-bordered" id="example1">
                                <thead>
                                <tr>
                                    <th>SN</th>
                                    <th>Amount Paid</th>
                                    <th>Reference</th>
                                    <th>Term</th>
                                    <th>Class</th>
                                    <th>Payment Status</th>
                                    <th>Payment Type</th>
                                    <th>Academic Session</th>
                                    <th>Created At</th>
                                    <th>Paid At</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>SN</th>
                                    <th>Amount Paid</th>
                                    <th>Reference</th>
                                    <th>Term</th>
                                    <th>Class</th>
                                    <th>Payment Status</th>
                                    <th>Payment Type</th>
                                    <th>Academic Session</th>
                                    <th>Created At</th>
                                    <th>Paid At</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <?php
                                    $sql = $db->query("SELECT p.*, c.name as class_name  FROM ".DB_PREFIX."payment p 
                                    LEFT JOIN ".DB_PREFIX."class c 
                                        ON p.class_id = c.id    
                                    WHERE p.student_id='$student_id' ORDER BY p.id DESC");
                                    while ($rs = $sql->fetch(PDO::FETCH_ASSOC)){
                                        ?>
                                        <tr>
                                            <td><?= $sn++ ?></td>
                                            <td><?= amount_format($rs['amount']) ?></td>
                                            <td><?= $rs['ref'] ?></td>
                                            <td><?= term($rs['term_id']) ?></td>
                                            <td><?= $rs['class_name'] ?></td>
                                            <td><?= $rs['status'] ?></td>
                                            <td><?= payment_type($rs['payment_type']) ?></td>
                                            <td><?= $rs['academic_session'] ?></td>
                                            <td><?= $rs['created_at'] ?></td>
                                            <td><?= $rs['paid_at'] ?></td>
                                        </tr>
                                        <?php
                                    }
                                ?>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="tab_3">
                        <a href="" class="btn btn-primary" data-toggle="modal" data-target="#modal-default2" style="margin-bottom: 20px">Add Attendance</a>

                        <div class="table-responsive">
                            <table class="table table-bordered" id="example">
                                <thead>
                                <tr>
                                    <th>SN</th>
                                    <th>Attendance</th>
                                    <th>Check-in / Check-out  Name</th>
                                    <th>Check-in / Check-out Phone Number</th>
                                    <th>Attendance Date</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>SN</th>
                                    <th>Attendance</th>
                                    <th>Check-in / Check-out  Name</th>
                                    <th>Check-in / Check-out Phone Number</th>
                                    <th>Attendance Date</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <?php
                                    $sql = $db->query("SELECT * FROM ".DB_PREFIX."attendance WHERE student_id='$student_id' ORDER BY id");
                                    while ($rs =  $sql->fetch(PDO::FETCH_ASSOC)){
                                        ?>
                                        <tr>
                                            <td><?= $sn++ ?></td>
                                            <td><?= ucwords($rs['attendance']) ?></td>
                                            <td><?= $rs['name'] ?></td>
                                            <td><?= $rs['phone'] ?></td>
                                            <td><?= $rs['attendance_date'] ?></td>
                                        </tr>
                                        <?php
                                    }
                                ?>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- /.tab-pane -->
                </div>
                <!-- /.tab-content -->
            </div>
            <!-- nav-tabs-custom -->
        </div>
    </div>
</section>

<?php require_once 'libs/foot.php'?>
