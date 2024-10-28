import request from '@/utils/request'

// 查询字典数据列表
export function listRelation(query) {
  return request({
    url: '/advertise/relation/data/list',
    method: 'get',
    params: query
  })
}
// 查询字典类型详细
export function getRelation(id) {
  return request({
    url: '/advertise/relation/data/' + id,
    method: 'get'
  })
}

// 新增字典类型
export function addRelation(data) {
  return request({
    url: '/advertise/relation/data',
    method: 'post',
    data: data
  })
}

// 修改字典类型
export function updateRelation(data) {
  return request({
    url: '/advertise/relation/data',
    method: 'put',
    data: data
  })
}

// 删除字典类型
export function delRelation(id) {
  return request({
    url: '/advertise/relation/data/' + id,
    method: 'delete'
  })
}
