package com.example.bankintentdemo.data

import androidx.compose.ui.graphics.Color
import com.example.bankintentdemo.model.MenuItem
import com.example.bankintentdemo.model.MenuSection
import com.example.bankintentdemo.model.MenuSectionLayout

val bankMenuSections = listOf(
    MenuSection(
        title = "상품가입/관리",
        iconText = "KB",
        iconColor = Color(0xFFFFC928),
        layout = MenuSectionLayout.Grid,
        items = listOf(
            MenuItem("추천상품"),
            MenuItem("예적금"),
            MenuItem("대출"),
            MenuItem("입출금"),
            MenuItem("퇴직연금"),
            MenuItem("펀드"),
            MenuItem("청약/채권"),
            MenuItem("ISA"),
            MenuItem("외화예금"),
            MenuItem("보험"),
            MenuItem("신탁"),
            MenuItem("골드/실버")
        )
    ),
    MenuSection(
        title = "조회",
        iconText = "Q",
        iconColor = Color(0xFF20AA93),
        items = listOf(
            MenuItem("전체계좌조회"),
            MenuItem("통합거래내역조회"),
            MenuItem("계좌관리", "비밀번호 관리, 계좌통합관리서비스(어카운트인포) 등")
        )
    ),
    MenuSection(
        title = "이체/출금",
        iconText = "→",
        iconColor = Color(0xFF2A9CEB),
        items = listOf(
            MenuItem("이체"),
            MenuItem("자동이체"),
            MenuItem("이체관리", "이체한도 조회/변경, 출금계좌등록/해지/등록방법변경 등")
        )
    ),
    MenuSection(
        title = "공과금",
        iconText = "TAX",
        iconColor = Color(0xFF1CB69B),
        items = listOf(MenuItem("공과금 납부/조회"))
    ),
    MenuSection(
        title = "자산관리",
        iconText = "●",
        iconColor = Color(0xFFE95B67),
        items = listOf(
            MenuItem("지출", "가계부, 카드관리, 정기지출"),
            MenuItem("마이데이터 설정")
        )
    ),
    MenuSection(
        title = "외환",
        iconText = "$",
        iconColor = Color(0xFFF3AE31),
        items = listOf(
            MenuItem("환율"),
            MenuItem("환전"),
            MenuItem("해외송금"),
            MenuItem("국내외화 이체/입출금"),
            MenuItem("외환정보 관리")
        )
    ),
    MenuSection(
        title = "지갑",
        iconText = "▰",
        iconColor = Color(0xFF76A9EA),
        items = listOf(
            MenuItem("모바일 신분증", "주민등록증, 운전면허증, 국가보훈증, 외국인증"),
            MenuItem("결제", "계좌기반 간편결제, 스타포인트 사용 가능"),
            MenuItem("NFT", "티켓도 디지털로! NFT에 안전보관"),
            MenuItem("공공알리미(국민비서 · 전자문서)")
        )
    ),
    MenuSection(
        title = "혜택",
        iconText = "🎁",
        iconColor = Color(0xFFF0F2F5),
        items = listOf(
            MenuItem("이벤트"),
            MenuItem("쿠폰함")
        )
    ),
    MenuSection(
        title = "생활",
        iconText = "+",
        iconColor = Color(0xFF8461E8),
        items = listOf(
            MenuItem("기차표(KTX · SRT) 예매"),
            MenuItem("여권 재발급 신청"),
            MenuItem("스마트항공권"),
            MenuItem("티머니 교통카드 충전")
        )
    ),
    MenuSection(
        title = "모바일 업무지원",
        iconText = "▭",
        iconColor = Color(0xFFBBD5E8),
        items = listOf(
            MenuItem("지점안내/번호표발행"),
            MenuItem("증명서 발급/제출", "정부24 전자증명서, 예금잔액증명서, 금융거래확인서 등"),
            MenuItem("통장/보안매체 재발급", "지점수령, STM수령, 등기우편수령"),
            MenuItem("전자영수증", "은행영수증, 구매영수증"),
            MenuItem("사고신고", "착오송금반환, 분실신고 등")
        )
    ),
    MenuSection(
        title = "멤버십",
        iconText = "★",
        iconColor = Color(0xFFF4C044),
        items = listOf(
            MenuItem("KB스타클럽"),
            MenuItem("급여클럽"),
            MenuItem("KB Youth Club/밀리터리 클럽", hasNewBadge = true)
        )
    ),
    MenuSection(
        title = "사업자",
        iconText = "▣",
        iconColor = Color(0xFFA46F3A),
        items = listOf(MenuItem("사장님+"))
    )
)

