<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="应用名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入应用名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告名称" prop="relationName">
        <el-input
          v-model="queryParams.relationName"
          placeholder="请输入广告名"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['advertise:relation:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="relationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="应用名称" align="center" prop="companyName" />
      <el-table-column label="广告位名称" align="center" prop="spaceName" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['advertise:relation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['advertise:relation:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="应用">
          <el-select v-model="form.companyId" placeholder="请选择"
                     @change="handleCompanyChange">
            <el-option
              v-for="item in companys"
              :key="item.id"
              :label="item.companyName"
              :value="item.id"
              :disabled="item.isDelete == 1"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="广告位">
          <el-select v-model="form.spaceId" placeholder="请选择"
                @change="handleSpaceChange">
            <el-option
              v-for="item in spaces"
              :key="item.id"
              :label="item.advName"
              :value="item.id"
              :disabled="item.isDelete == 1"
            ></el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listRelation, getRelation, addRelation, updateRelation, delRelation} from "@/api/advertise/relation";
import {listCompany} from "@/api/advertise/company";
import {listSpace} from "@/api/advertise/space";

export default {
  name: "Relation",
  dicts: ['adv_relation_type', 'adv_relation_platform', 'sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 字典表格数据
      relationList: [],
      companys: [],
      spaces: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        advPlatform: undefined,
        advType: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询字典类型列表 */
    getList() {
      this.loading = true;
      listRelation(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.relationList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        companyId: undefined,
        companyName: undefined,
        spaceId: undefined,
        spaceName: undefined
      };
      listCompany().then(response => {
        this.companys = response.rows;
      })
      listSpace().then(response => {
        this.spaces = response.rows;
      })
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加应用广告关系";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRelation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改应用广告关系";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateRelation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log(this.form)
            addRelation(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除应用广告关系编号为"' + id + '"的数据项？').then(function() {
        return delRelation(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleCompanyChange(value) {
      const selectedOption = this.companys.find(option => option.id === value);
      if (selectedOption) {
        this.form.companyId = selectedOption.id; // 更新 advId
        this.form.companyName = selectedOption.companyName
      } else {
        this.form.companyName = '前端错误了！！！！'
      }
    },
    handleSpaceChange(value) {
      const selectedOption = this.spaces.find(option => option.id === value);
      console.log(value)
      console.log(this.spaces)
      console.log(selectedOption)
      if (selectedOption) {
        this.form.spaceId = selectedOption.id; // 更新 advId
        this.form.spaceName = selectedOption.advName
      } else {
        this.form.spaceName = '前端错误了！！！！'
      }
    }
  }
};
</script>
