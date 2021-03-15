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

require_once 'libs/head.php';
?>

<section class="content">
    <div class="row">

        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">

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

                        <a href="" class="btn btn-primary" style="margin-bottom: 20px;">Add Payment</a>

                        <div class="table-responsive">
                            <table class="table table-bordered" id="example1">
                                <thead>
                                <tr>
                                    <th>SN</th>
                                    <th>Amount Paid</th>
                                    <th>Reference</th>
                                    <th>Term</th>
                                    <th>Class</th>
                                    <th>Status</th>
                                    <th>Academic Session</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>SN</th>
                                    <th>Amount Paid</th>
                                    <th>Reference</th>
                                    <th>Term</th>
                                    <th>Class</th>
                                    <th>Status</th>
                                    <th>Academic Session</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                    <!-- /.tab-pane -->
                    <div class="tab-pane" id="tab_3">
                        <a href="" class="btn btn-primary" style="margin-bottom: 20px">Add Attendance</a>

                        <div class="table-responsive">
                            <table class="table table-bordered" id="example">
                                <thead>
                                <tr>
                                    <th>SN</th>
                                    <th>Attendance</th>
                                    <th>Date</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>SN</th>
                                    <th>Attendance</th>
                                    <th>Date</th>
                                </tr>
                                </tfoot>
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
