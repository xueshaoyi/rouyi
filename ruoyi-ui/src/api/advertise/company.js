import request from '@/utils/request'

// 查询字典数据列表
export function listCompany(query) {
  return request({
    url: '/advertise/company/data/list',
    method: 'get',
    params: query
  })
}
// 查询字典类型详细
export function getCompany(id) {
  return request({
    url: '/advertise/company/data/' + id,
    method: 'get'
  })
}

// 新增字典类型
export function addCompany(data) {
  return request({
    url: '/advertise/company/data',
    method: 'post',
    data: data
  })
}

  // 修改字典类型
export function updateCompany(data) {
  return request({
    url: '/advertise/company/data',
    method: 'put',
    data: data
  })
}

// 删除字典类型
export function delCompany(id) {
  return request({
    url: '/advertise/company/data/' + id,
    method: 'delete'
  })
}
