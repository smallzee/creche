<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 12/21/20
 * Time: 8:56 PM
 */

$page_title = "Parent Information";
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
                        <table class="table table-bordered table-striped" id="example1">
                            <thead>
                            <tr>
                                <th>SN</th>
                                <th>Parent Id</th>
                                <th>Full Name</th>
                                <th>Email Address</th>
                                <th>Phone Number</th>
                                <th>Occupation</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>SN</th>
                                <th>Parent Id</th>
                                <th>Full Name</th>
                                <th>Email Address</th>
                                <th>Phone Number</th>
                                <th>Occupation</th>
                                <th>Actions</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </div>
    </div>
</section>

<?php require_once 'libs/foot.php'?>
