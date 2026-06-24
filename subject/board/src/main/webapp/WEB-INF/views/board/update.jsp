<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>
<h1 class="page-header my-4"><i class="far fa-edit"></i> 글 수정</h1>
<div>
    <c:if test="${not empty board.attaches}">
        <div class="mb-3">
            <label>기존 첨부파일</label>
            <ul class="list-group">
                <c:forEach items="${board.attaches}" var="attach">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>
                            <a href="download?no=${attach.no}">${attach.filename}</a>
                            (${attach.fileSize})
                        </span>
                        <form method="post" action="attachment/delete" class="m-0">
                            <input type="hidden" name="no" value="${attach.no}">
                            <input type="hidden" name="bno" value="${board.no}">
                            <button type="submit" class="btn btn-sm btn-danger">
                                <i class="fas fa-trash-alt"></i> 삭제
                            </button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form role="form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="no" value="${board.no}">
        <div>
            <label>제목</label>
            <input name="title" class="form-control" value="${board.title}">
        </div>
        <div>
            <label>작성자</label>
            <input name="writer" class="form-control" value="${board.writer}">
        </div>
        <div>
            <label>내용</label>
            <textarea class="form-control" name="content" rows="10">${board.content}</textarea>
        </div>
        <div class="mt-3">
            <label>새 첨부파일</label>
            <input type="file" name="files" class="form-control" multiple>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> 확인</button>
            <button type="reset" class="btn btn-primary"><i class="fas fa-undo"></i> 취소</button>
            <a href="get?no=${board.no}" class="btn btn-primary"><i class="fas fa-file-alt"></i> 돌아가기</a>
        </div>
    </form>
</div>
<%@include file="../layouts/footer.jsp"%>
