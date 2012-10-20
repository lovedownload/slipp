Feature: 질문 답변 게시판

Scenario: 질문이 정상적으로 등록되어야 한다.
	Given SLiPP 메인 페이지에서 로그인 후 글쓰기 버튼을 클릭한다.
	When 제목 지속 가능한 삶, 프로그래밍, 프로그래머 입력한다.
	And 내용 이 공간은 삶과 일의 균형을 맞추면서 지속 가능한 삶을 살아갈 것인가에 고민을 담기 위한 곳이다. 입력한다.
	And 태그 java javascript 입력한다.
	And 질문하기 버튼을 클릭한다.
	Then 입력한 데이터가 정상적으로 등록되었는지 확인한다.

Scenario: 태그 풀에 존재하는 태그가 정상적으로 등록되어야 한다.
	Given SLiPP 메인 페이지에서 로그인 후 글쓰기 버튼을 클릭한다.
	When 태그 java javascript 입력한다.
	And 질문하기 버튼을 클릭한다.
	Then 입력한 데이터가 정상적으로 등록되었는지 확인한다. 태그는 java javascript가 등록되어야 한다.

Scenario: 태그 풀에 존재하지 않는 태그는 질문에 등록되지 않아야 한다.
	Given SLiPP 메인 페이지에서 로그인 후 글쓰기 버튼을 클릭한다.
	When 태그 java javascript newtag 입력한다.
	And 질문하기 버튼을 클릭한다.
	Then 입력한 데이터가 정상적으로 등록되었는지 확인한다. 태그는 java javascript가 등록되어야 한다.
	And 신규 태그 목록에서 newtag를 볼 수 있어야 한다.  