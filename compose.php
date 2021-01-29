<?php
/**
 * Created by PhpStorm.
 * User: Tech4all
 * Date: 1/29/21
 * Time: 10:43 AM
 */

$page_title = "Compose Message";
require_once 'config/core.php';
require_once 'libs/head.php';
?>

<section class="content">
    <div class="row">
        <div class="col-md-12">

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

                    <form action="" method="post">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="">Subject</label>
                                    <input type="text" class="form-control" required placeholder="Subject" name="subject" id="">
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="">To</label>
                                    <select name="to[]" class="form-control select2" required multiple id="">
                                        <?php
                                            $sql = $db->query("SELECT * FROM ".DB_PREFIX."parents ORDER BY fname");
                                            while ($rs = $sql->fetch(PDO::FETCH_ASSOC)){
                                                ?>
                                                <option value="<?= $rs['id'] ?>"><?= ucwords($rs['fname']) ?> <?= !(empty($rs['email'])) ? '('.$rs['email'].')' : '' ?></option>
                                                <?php
                                            }
                                        ?>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="">Message</label>
                                    <textarea name="message" id="" required class="form-control" style="resize: none; min-height: 100px;" placeholder="Message"></textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="submit" name="send" class="btn btn-primary" value="Send" id="">
                        </div>
                    </form>

                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </div>
    </div>
</section>

<?php require_once 'libs/foot.php';?>
