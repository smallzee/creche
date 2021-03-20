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
                                <th>Application Id</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>
            </div>


        </div>
    </div>
</section>

<?php require_once 'libs/foot.php';?>
