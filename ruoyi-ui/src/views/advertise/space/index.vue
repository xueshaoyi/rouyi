<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="广告类型" prop="advType">
        <el-select
          v-model="queryParams.advType"
          placeholder="广告类型"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.adv_space_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="广告平台" prop="advPlatform">
        <el-select
          v-model="queryParams.advPlatform"
          placeholder="平台渠道"
          clearable
          size="small"
          style="width: 300px"
        >
          <el-option
            v-for="dict in dict.type.adv_space_platform"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['advertise:space:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="spaceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="广告位 id " align="center" prop="advId" />
      <el-table-column label="app id " align="center" prop="appId" />
      <el-table-column label="广告位名称" align="center" prop="advName" :show-overflow-tooltip="true" />
      <el-table-column prop="advType" label="广告类型" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.adv_space_type" :value="scope.row.advType"/>
        </template>
      </el-table-column>
      <el-table-column prop="advPlatform" label="广告平台" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.adv_space_platform" :value="scope.row.advPlatform"/>
        </template>
      </el-table-column>
      <el-table-column prop="advPlatform" label="上架渠道" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.adv_project_platform" :value="scope.row.projectPlatform"/>
        </template>
      </el-table-column>
      <el-table-column prop="isDelete" label="是否启用" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.isDelete"/>
        </template>
      </el-table-column>
      <el-table-column label="分配比例" align="center" prop="distributton" />
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
            v-hasPermi="['advertise:space:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['advertise:space:remove']"
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
        <el-form-item label="广告名称" prop="advName">
          <el-input v-model="form.advName" placeholder="请输入广告位名称" />
        </el-form-item>
        <el-form-item label="上架渠道">
          <el-select
            v-model="form.projectPlatform"
            placeholder="上架渠道"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.adv_project_platform"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="广告平台">
          <el-select
            v-model="form.advPlatform"
            placeholder="广告平台"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.adv_space_platform"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="appId" prop="advId">
          <el-input v-model="form.appId" placeholder="请输入appId" />
        </el-form-item>
        <el-form-item label="广告位Id" prop="advId">
          <el-input v-model="form.advId" placeholder="请输入广告位Id" />
        </el-form-item>
        <el-form-item label="广告类型">
          <el-select
            v-model="form.advType"
            placeholder="广告类型"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.adv_space_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="分配比例" prop="distributton">
          <el-input v-model="form.distributton" placeholder="请输入分配比例" />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-select
            v-model="form.isDelete"
            placeholder="是否启用"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
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
import {listSpace, getSpace, addSpace, updateSpace, delSpace} from "@/api/advertise/space";

export default {
  name: "Space",
  dicts: ['adv_space_type', 'adv_space_platform', 'sys_normal_disable', 'adv_project_platform'],
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
      spaceList: [],
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
      listSpace(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.spaceList = response.rows;
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
        appId: undefined,
        advId: undefined,
        advName: undefined,
        advType: undefined,
        advPlatform: undefined,
        projectPlatform: undefined,
        distributton: undefined
      };
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
      this.title = "添加广告位";
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
      getSpace(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改广告位";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateSpace(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSpace(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除广告位编号为"' + id + '"的数据项？').then(function() {
        return delSpace(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
