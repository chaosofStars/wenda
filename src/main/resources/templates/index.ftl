<#include "header.ftl">

    <div class="zg-wrap zu-main clearfix " role="main">
        <div class="zu-main-content">
            <div class="zu-main-content-inner">
                <div class="zg-section" id="zh-home-list-title">
                    <i class="zg-icon zg-icon-feedlist"></i>最新动态
                    <input type="hidden" id="is-topstory">
                    <span class="zg-right zm-noti-cleaner-setting" style="list-style:none">
                        <a href="https://nowcoder.com/settings/filter" class="zg-link-gray-normal">
                            <i class="zg-icon zg-icon-settings"></i>设置</a></span>
                </div>
                <div class="zu-main-feed-con navigable" data-feedtype="topstory" id="zh-question-list" data-widget="navigable" data-navigable-options="{&quot;items&quot;:&quot;&gt; .zh-general-list .feed-content&quot;,&quot;offsetTop&quot;:-82}">
                    <a href="javascript:;" class="zu-main-feed-fresh-button" id="zh-main-feed-fresh-button" style="display:none"></a>
                    <div id="js-home-feed-list" class="zh-general-list topstory clearfix" data-init="{&quot;params&quot;: {}, &quot;nodename&quot;: &quot;TopStory2FeedList&quot;}" data-delayed="true" data-za-module="TopStoryFeedList">
                        <div class="feed-item folding feed-item-hook feed-item-1
                        " feed-item-p="" data-type="p" id="feed-1" data-za-module="FeedItem" data-za-index="">
                            <#list vos as vo>
                            <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                            <div class="feed-item-inner">
                                <div class="avatar">
                                    <a title="李淼" data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="https://nowcoder.com/people/amuro1230">
                                        <img src="${vo.user.headUrl}" class="zm-item-img-avatar"></a>
                                </div>
                                <div class="feed-main">
                                    <div class="feed-content" data-za-module="AnswerItem">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <h2 class="feed-title">
                                            <a class="question_link" target="_blank" href="/user/${vo.user.id}">${vo.question.title}</a></h2>
                                        <div class="feed-question-detail-item">
                                            <div class="question-description-plain zm-editable-content"></div>
                                        </div>
                                        <div class="expandable entry-body">
                                            <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="123114" data-action="/answer/content" data-author-name="${vo.user.name}" data-entry-url="/question/19857995/answer/13174385">

                                                <div class="zh-summary summary clearfix">${vo.question.content}<br>
                                                    ${vo.question.createdDate?string("yyyy-MM-dd HH:mm:ss")}
                                                    <a href="https://nowcoder.com/question/19857995/answer/13174385" class="toggle-expand">显示全部</a></div>
                                                <p class="visible-expanded">
                                                    <a itemprop="url" class="answer-date-link meta-item" data-tip="s$t$发布于 2011-09-23" target="_blank" href="https://nowcoder.com/question/19857995/answer/13174385">编辑于 2015-06-24</a></p>
                                            </div>
                                        </div>
                                        <div class="feed-meta">
                                            <div class="zm-item-meta answer-actions clearfix js-contentActions">
                                                <div class="zm-meta-panel">
                                                    <a data-follow="q:link" class="follow-link zg-follow meta-item" href="javascript:;" id="sfb-123114">
                                                        <i class="z-icon-follow"></i>关注问题</a>
                                                    <a href="#" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                                        <i class="z-icon-comment"></i>${vo.question.commentCount} 条评论</a>
                                                    <a href="#" class="meta-item js-thank" data-thanked="false">
                                                        <i class="z-icon-thank"></i>感谢</a>


                                                    <button class="meta-item item-collapse js-collapse">
                                                        <i class="z-icon-fold"></i>收起</button>
                                                </div>
                                            </div>
                                            <a href="#" class="ignore zu-autohide" name="dislike" data-tip="s$b$不感兴趣"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="undo-dislike-options" data-item_id="2">此内容将不会在动态中再次显示
                                <span class="zg-bull">•</span>
                                <a href="#" class="meta-item revert">撤销</a>
                                <a href="#" class="ignore zu-autohide close"></a>
                            </div>
                        </div>
                        <div class="feed-item folding feed-item-hook feed-item-3
                        " feed-item-a="" data-type="a" id="feed-3" data-za-module="FeedItem" data-za-index="">
                        </#list>
<#include "footer.ftl">