
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>jQuery File Upload Demo</title>
        <meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
        <meta name="viewport" content="width=device-width">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-image-gallery.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fileupload-ui.css">
        
        <script src="${pageContext.request.contextPath}/js/upload/jquery-1.8.2.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/vendor/jquery.ui.widget.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/tmpl.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/load-image.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/canvas-to-blob.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/bootstrap-image-gallery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/jquery.iframe-transport.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/jquery.fileupload.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/jquery.fileupload-fp.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/jquery.fileupload-ui.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/locale.js"></script>
	    <script src="${pageContext.request.contextPath}/js/upload/main.js"></script>
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
            </div>
        </div>
        <div class="container">
            <form id="fileupload" action="UploadServlet" method="POST" enctype="multipart/form-data">
                <div class="row fileupload-buttonbar">
                    <div class="span7">
                        <span class="btn btn-success fileinput-button">
                            <i class="icon-plus icon-white"></i>
                            <span>添加图片</span>
                            <input type="file" name="files[]" multiple>
                        </span>
                        <button type="submit" class="btn btn-primary start">
                            <i class="icon-upload icon-white"></i>
                            <span>开始上传</span>
                        </button>
                        <button type="reset" class="btn btn-warning cancel">
                            <i class="icon-ban-circle icon-white"></i>
                            <span>取消上传</span>
                        </button>
                        <button type="button" class="btn btn-danger delete">
                            <i class="icon-trash icon-white"></i>
                            <span>删除</span>
                        </button>
                    </div>
                    <div class="span5 fileupload-progress fade">
                        <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                            <div class="bar" style="width:0%;"></div>
                        </div>
                        <div class="progress-extended">&nbsp;</div>
                    </div>
                </div>
                <div class="fileupload-loading"></div>
                <br>
                <table role="presentation" class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
            </form>
            <br>
        </div>
        <script id="template-upload" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td class="preview"><span class="fade"></span></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
            {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>开始</span>
                </button>
                {% } %}</td>
            {% } else { %}
            <td colspan="2"></td>
            {% } %}
            <td class="cancel">{% if (!i) { %}
                <button class="btn btn-warning">
                    <i class="icon-ban-circle icon-white"></i>
                    <span>取消</span>
                </button>
                {% } %}</td>
        </tr>
        {% } %}
    </script>
    <script id="template-download" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
            {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
                <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img src="{%=file.thumbnail_url%}"></a>
                {% } %}</td>
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
            {% } %}
            <td class="delete">
                <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}"{% if (file.delete_with_credentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                        <i class="icon-trash icon-white"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1">
            </td>
        </tr>
        {% } %}
    </script>

   
</body> 
</html>