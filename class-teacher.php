<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 12/21/20
 * Time: 4:01 PM
 */

$page_title = "Student Class Teachers";
require_once 'config/core.php';
require_once 'libs/head.php';
?>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add New Class Teacher</h4>
            </div>
            <div class="modal-body">
                <form action="" method="post">

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Staff Name</label>
                                <select name="staff-id" required id="" class="form-control">
                                    <option value="" disabled selected>Select</option>
                                    <?php
                                    $sql = $db->query("SELECT * FROM ".DB_PREFIX."admin WHERE role=2 ORDER BY fname");
                                    while ($rs = $sql->fetch(PDO::FETCH_ASSOC)){
                                        ?>
                                        <option value="<?= $rs['id'] ?>"><?= ucwords($rs['fname']) ?> (<?= $rs['username'] ?>)</option>
                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Class Name</label>
                                <select name="class-id" required id="" class="form-control">
                                    <option value="" disabled selected>Select</option>
                                    <?php
                                        $sql = $db->query("SELECT * FROM ".DB_PREFIX."class ORDER BY name");
                                        while ($rs = $sql->fetch(PDO::FETCH_ASSOC)){
                                            ?>
                                            <option value="<?= $rs['id'] ?>"><?= ucwords($rs['name']) ?></option>
                                            <?php
                                        }
                                    ?>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Term</label>
                                <select name="term" class="form-control" required id="">
                                    <option value="" disabled selected>Select</option>
                                    <option value="1">First Term</option>
                                    <option value="2">Second Term</option>
                                    <option value="3">Third Term</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="">Academic Session</label>
                                <select name="session" class="form-control" required id="">
                                    <option value="" disabled selected>Select</option>
                                    <?php
                                        foreach (range('2019',date('Y')) as $value){
                                            $start = $value-1;
                                            ?>
                                            <option value="<?= $start.'-'.$value ?>"><?= $start.'-'.$value ?></option>
                                            <?php
                                        }
                                    ?>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" name="add" class="btn btn-primary btn-sm" value="Submit" id="">
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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

                    <button type="button" class="btn btn-default mb-20" data-toggle="modal" data-target="#modal-default">
                        Add New Class Teacher
                    </button>

                    <div class="table-responsive">
                        <table class="table-bordered table-striped table" id="example1">
                            <thead>
                            <tr>
                                <th>SN</th>
                                <th>Class Teacher Id</th>
                                <th>Class Teacher Name</th>
                                <th>Term</th>
                                <th>Class Name</th>
                                <th>Academic Session</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>SN</th>
                                <th>Class Teacher Id</th>
                                <th>Class Teacher Name</th>
                                <th>Term</th>
                                <th>Class Name</th>
                                <th>Academic Session</th>
                                <th>Actions</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                </div>

            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<?php require_once 'libs/foot.php'?>
