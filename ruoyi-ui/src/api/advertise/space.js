import request from '@/utils/request'

// 查询字典数据列表
export function listSpace(query) {
  return request({
    url: '/advertise/space/data/list',
    method: 'get',
    params: query
  })
}
// 查询字典类型详细
export function getSpace(id) {
  return request({
    url: '/advertise/space/data/' + id,
    method: 'get'
  })
}

// 新增字典类型
export function addSpace(data) {
  return request({
    url: '/advertise/space/data',
    method: 'post',
    data: data
  })
}

// 修改字典类型
export function updateSpace(data) {
  return request({
    url: '/advertise/space/data',
    method: 'put',
    data: data
  })
}

// 删除字典类型
export function delSpace(id) {
  return request({
    url: '/advertise/space/data/' + id,
    method: 'delete'
  })
}
