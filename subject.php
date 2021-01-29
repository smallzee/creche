<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 1/29/21
 * Time: 11:54 AM
 */

$page_title = "Subject";
require_once 'config/core.php';
require_once 'libs/head.php';
?>

<section class="content">
    <div class="row">
        <div class="col-md-12">

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

                </div>
            </div>

        </div>
    </div>
</section>

<?php require_once 'libs/foot.php'?>
