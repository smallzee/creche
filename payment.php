<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 3/15/21
 * Time: 1:44 PM
 */

$page_title = "Payment History";
require_once 'config/core.php';
require_once 'libs/head.php';
?>

<section class="content">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title"><?= $page_title ?></h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body">

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
                                    ORDER BY p.id DESC");
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
            </div>


        </div>
    </div>
</section>

<?php require_once 'libs/foot.php';?>
